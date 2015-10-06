package com.github.awvalenti.bauhinia.forficata;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.github.awvalenti.bauhinia.forficata.listeners.ForficataWiimoteFullListener;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;

class BlueCoveListener implements DiscoveryListener {

	private final L2CAPWiimoteFactory factory = new L2CAPWiimoteFactory();
	private final BluetoothDeviceIdentifier deviceIdentifier = new BluetoothDeviceIdentifier();

	private final ForficataObserver observer;
	private final ForficataWiimoteFullListener wiimoteListener;
	private final BluetoothDiscoveryManager bluetoothDiscoveryManager;

	public BlueCoveListener(ForficataWiimoteFullListener wiimoteListener,
			final ForficataObserver observer, final Object monitor) {
		this.wiimoteListener = wiimoteListener;
		this.observer = observer;
		this.bluetoothDiscoveryManager = new BluetoothDiscoveryManager(new Runnable() {
			@Override
			public void run() {
				observer.searchFinished();
				synchronized (monitor) {
					monitor.notify();
				}
			}
		});
	}

	@Override
	public synchronized void deviceDiscovered(final RemoteDevice device, final DeviceClass clazz) {
		bluetoothDiscoveryManager.beginDeviceDiscovered();
		new Thread() {
			@Override
			public void run() {
				try {
					handleDeviceDiscovered(device, clazz);
				} finally {
					bluetoothDiscoveryManager.endDeviceDiscovered();
				}
			}
		}.start();
	}

	@Override
	public synchronized void inquiryCompleted(int reason) {
		bluetoothDiscoveryManager.inquiryCompleted();
	}

	private void handleDeviceDiscovered(RemoteDevice device, DeviceClass clazz) {
		observer.bluetoothDeviceFound(device.getBluetoothAddress(), ((Object) clazz).toString());

		try {
			deviceIdentifier.assertDeviceIsWiimote(device);
			observer.wiimoteIdentified();
			Wiimote wiimote = factory.createWiimote(device, wiimoteListener);
			observer.wiimoteConnected(wiimote);

		} catch (DeviceRejectedIdentification e) {
			// observer.deviceRejectedIdentification();

		} catch (IdentifiedAnotherDevice e) {
			// observer.identifiedAnotherDevice();

		} catch (WiimoteRejectedConnection e) {
			observer.errorOccurred(ForficataExceptionFactory.wiimoteRejectedConnection(e.getCause()));
		}

	}

	@Override
	public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
	}

	@Override
	public void serviceSearchCompleted(int transID, int respCode) {
	}

}