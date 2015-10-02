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
	private int numberOfWiimotesConnected = 0;

	public BlueCoveWiimoteConnector(ReadableForficataConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		ForficataObserver observer = config.getForficataObserver();
		observer.forficataStarted();

		try {
			blueCoveLib = new BlueCoveLibraryFacade();
			observer.librariesLoaded();

			Object monitor = new Object();
			blueCoveLib.startAsynchronousSearch(new BlueCoveListener(observer, monitor,
					new BluetoothDeviceIdentifier()));
			observer.searchStarted();

			if (config.isSynchronous()) {
				synchronized (monitor) {
					monitor.wait();
				}
			}

		} catch (BluetoothStateException e) {
			observer.errorOccurred(ForficataExceptionFactory.correspondingTo(e));

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private class BlueCoveListener implements DiscoveryListener {

		private final L2CAPWiimoteFactory factory = new L2CAPWiimoteFactory();

		private final ForficataObserver observer;
		private final Object monitor;
		private final BluetoothDeviceIdentifier deviceIdentifier;

		public BlueCoveListener(ForficataObserver observer, Object monitor,
				BluetoothDeviceIdentifier deviceIdentifier) {
			this.observer = observer;
			this.monitor = monitor;
			this.deviceIdentifier = deviceIdentifier;
		}

		@Override
		public synchronized void deviceDiscovered(RemoteDevice device, DeviceClass clazz) {
			observer.bluetoothDeviceFound(device.getBluetoothAddress(),
					((Object) clazz).toString());

			boolean isWiimote;
			try {
				isWiimote = deviceIdentifier.isWiimote(device);
			} catch (IOException e) {
				// TODO
//				observer.deviceIdentificationFailed();
				return;
			}

			if (!isWiimote) {
				// TODO
//				observer.nonWiimoteIdentified();
				return;
			}

			observer.wiimoteIdentified();

			Wiimote wiimote;
			try {
				wiimote = factory.createWiimote(device, config.getWiimoteListener());
			} catch (IOException e) {
				observer.errorOccurred(ForficataExceptionFactory.wiimoteRejectedConnection(e));
				return;
			}

			++numberOfWiimotesConnected;
			observer.wiimoteConnected(wiimote);
			if (numberOfWiimotesConnected >= config.getWiimotesExpected()) blueCoveLib.stopSearch();
		}

		@Override
		public synchronized void inquiryCompleted(int reason) {
			observer.searchFinished();
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
