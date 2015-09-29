package com.github.awvalenti.bauhinia.forficata;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;

class BlueCoveWiimoteConnector implements WiimoteConnector {

	private final ReadableForficataConfig config;

	private BlueCoveLibraryFacade blueCoveLib;

	private int numberOfWiimotesFound = 0;

	public BlueCoveWiimoteConnector(ReadableForficataConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		ForficataObserver listener = config.getForficataEventListener();
		listener.forficataStarted();

		try {
			blueCoveLib = new BlueCoveLibraryFacade();
			listener.librariesLoaded();

			Object monitor = new Object();
			blueCoveLib.startAsynchronousSearch(new BlueCoveListener(listener, monitor));
			listener.searchStarted();

			if (config.isSynchronous()) {
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

		private final ForficataObserver listener;
		private final Object monitor;

		public BlueCoveListener(ForficataObserver listener, Object monitor) {
			this.listener = listener;
			this.monitor = monitor;
		}

		@Override
		public synchronized void deviceDiscovered(RemoteDevice device, DeviceClass clazz) {
			listener.bluetoothDeviceFound(device.getBluetoothAddress(),
					((Object) clazz).toString());

			boolean deviceIsWiimote;
			try {
				deviceIsWiimote = factory.deviceIsWiimote(device);
			} catch (IOException e) {
				listener.errorOccurred(ForficataExceptionFactory.deviceRejectedIdentification(e));
				return;
			}

			if (deviceIsWiimote) {
				listener.wiimoteIdentified();
				try {
					Wiimote wiimote = factory.createWiimote(device, config.getWiimoteListener());
					listener.wiimoteConnected(wiimote);
					if (++numberOfWiimotesFound >= config.getWiimotesExpected()) {
						blueCoveLib.stopSearch();
					}
				} catch (IOException e) {
					listener.errorOccurred(ForficataExceptionFactory.wiimoteRejectedConnection(e));
				}
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
