package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.github.awvalenti.bauhinia.coronata.L2CAPWiiRemoteFactory.DeviceRejectedIdentification;
import com.github.awvalenti.bauhinia.coronata.L2CAPWiiRemoteFactory.IdentifiedAnotherDevice;
import com.github.awvalenti.bauhinia.coronata.L2CAPWiiRemoteFactory.WiiRemoteRejectedConnection;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;

class BlueCoveListener implements DiscoveryListener {

	private final L2CAPWiiRemoteFactory wiiRemotefactory = new L2CAPWiiRemoteFactory();
	private final CoronataExceptionFactory exceptionFactory;

	private final WiiRemoteFullListener wiiRemoteListener;
	private final CoronataFullObserver observer;
	private final JobSynchronizer synchronizer;

	public BlueCoveListener(CoronataExceptionFactory exceptionFactory,
			WiiRemoteFullListener wiiRemoteListener, final CoronataFullObserver observer,
			final Object monitor) {
		this.exceptionFactory = exceptionFactory;
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

		// This method, deviceDiscovered, should return immediately, according
		// to BlueCove documentation. For this reason, we handle device discoveries
		// on separate threads. However, this requires synchronization.

		synchronizer.addJob(new Runnable() {
			@Override
			public void run() {
				handleDeviceDiscovered(device, clazz);
			}
		});
	}

	@Override
	public synchronized void inquiryCompleted(int reason) {
		synchronizer.finishedAddingJobs();
	}

	private void handleDeviceDiscovered(RemoteDevice device, DeviceClass clazz) {
		String address = device.getBluetoothAddress();
		String deviceClass = ((Object) clazz).toString();

		observer.bluetoothDeviceFound(address, deviceClass);

		try {
			wiiRemotefactory.assertDeviceIsWiiRemote(device);
			observer.wiiRemoteIdentified();
			WiiRemote wiiRemote = wiiRemotefactory.createWiiRemote(device, wiiRemoteListener);
			observer.wiiRemoteConnected(wiiRemote);

		} catch (DeviceRejectedIdentification e) {
			observer.deviceRejectedIdentification(address, deviceClass);

		} catch (IdentifiedAnotherDevice e) {
			observer.deviceIdentifiedAsNotWiiRemote(address, deviceClass);

		} catch (WiiRemoteRejectedConnection e) {
			observer.errorOccurred(exceptionFactory.wiiRemoteRejectedConnection(e.getCause()));
		}

	}

	@Override
	public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
	}

	@Override
	public void serviceSearchCompleted(int transID, int respCode) {
	}

}