package com.github.awvalenti.bauhinia.forficata;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

class BlueCoveWiimoteConnector implements WiimoteConnector {

	private final ForficataConfiguration config;

	private BlueCoveLibraryFacade blueCoveLib;

	private int numberOfWiimotesFound = 0;

	public BlueCoveWiimoteConnector(ForficataConfiguration config) {
		this.config = config;
	}

	@Override
	public void start() {
		ForficataEventListener listener = config.forficataEventListener;
		try {
			blueCoveLib = new BlueCoveLibraryFacade();
			listener.librariesLoaded();

			Object monitor = new Object();
			blueCoveLib.startAsynchronousSearch(new BlueCoveListener(listener, monitor));
			listener.searchStarted();

			if (config.synchronous) {
				synchronized (monitor) {
					monitor.wait();
				}
			}

		} catch (BluetoothStateException e) {
			listener.errorOccurred(ForficataExceptionFactory.correspondingTo(e));

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private class BlueCoveListener implements DiscoveryListener {

		private final L2CAPWiimoteFactory factory = new L2CAPWiimoteFactory();

		private final ForficataEventListener listener;
		private final Object monitor;

		public BlueCoveListener(ForficataEventListener listener, Object monitor) {
			this.listener = listener;
			this.monitor = monitor;
		}

		@Override
		public synchronized void deviceDiscovered(RemoteDevice device, DeviceClass clazz) {
			listener.bluetoothDeviceFound(device.getBluetoothAddress(),
					((Object) clazz).toString());
			try {
				if (factory.deviceIsWiimote(device)) {
					listener.wiimoteIdentified();
					Wiimote wiimote = factory.createWiimote(device, config.wiimoteEventListener);
					listener.wiimoteConnected(wiimote);
					if (++numberOfWiimotesFound >= config.wiimotesExpected) {
						blueCoveLib.stopSearch();
					}
				}
			} catch (IOException e) {
				listener.errorOccurred(ForficataExceptionFactory.connectionRefused(e));
			}
		}

		@Override
		public synchronized void inquiryCompleted(int reason) {
			listener.searchFinished();
			synchronized (monitor) {
				monitor.notify();
			}
		}

		@Override
		public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
		}

		@Override
		public void serviceSearchCompleted(int transID, int respCode) {
		}

	}

}
