package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.github.awvalenti.bauhinia.coronata.WiiRemote;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;

class BlueCoveListener implements DiscoveryListener {

	private final L2CAPWiiRemoteFactory factory = new L2CAPWiiRemoteFactory();
	private final BluetoothDeviceIdentifier deviceIdentifier = new BluetoothDeviceIdentifier();

	private final CoronataFullObserver observer;
	private final WiiRemoteFullListener wiiRemoteListener;
	private final JobSynchronizer synchronizer;

	public BlueCoveListener(WiiRemoteFullListener wiiRemoteListener,
			final CoronataFullObserver observer, final Object monitor) {
		this.wiiRemoteListener = wiiRemoteListener;
		this.observer = observer;
		this.synchronizer = new JobSynchronizer(new Runnable() {
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

		// This method, deviceDiscovered, should return immediatelly, according
		// to BlueCove documentation. For that reason, we handle the discovery
		// of a device in a separate thread. However, this requires
		// synchronization.

		synchronizer.newJob(new Runnable() {
			@Override
			public void run() {
				handleDeviceDiscovered(device, clazz);
			}
		});
	}

	@Override
	public synchronized void inquiryCompleted(int reason) {
		synchronizer.end();
	}

	private void handleDeviceDiscovered(RemoteDevice device, DeviceClass clazz) {
		String address = device.getBluetoothAddress();
		String deviceClass = ((Object) clazz).toString();

		observer.bluetoothDeviceFound(address, deviceClass);

		try {
			deviceIdentifier.assertDeviceIsWiiRemote(device);
			observer.wiiRemoteIdentified();
			WiiRemote wiiRemote = factory.createWiiRemote(device, wiiRemoteListener);
			observer.wiiRemoteConnected(wiiRemote);

		} catch (DeviceRejectedIdentification e) {
			observer.deviceRejectedIdentification(address, deviceClass);

		} catch (IdentifiedAnotherDevice e) {
			observer.deviceIdentifiedAsNotWiiRemote(address, deviceClass);

		} catch (WiiRemoteRejectedConnection e) {
			observer.errorOccurred(CoronataExceptionFactory.wiiRemoteRejectedConnection(e.getCause()));
		}

	}

	@Override
	public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
	}

	@Override
	public void serviceSearchCompleted(int transID, int respCode) {
	}

}