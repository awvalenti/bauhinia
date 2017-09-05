package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.github.awvalenti.bauhinia.coronata.WiiRemoteFactory.DeviceIdentifiedAsNotWiiRemote;
import com.github.awvalenti.bauhinia.coronata.WiiRemoteFactory.DeviceRejectedIdentification;
import com.github.awvalenti.bauhinia.coronata.WiiRemoteFactory.WiiRemoteRejectedConnection;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class WiiRemoteDiscoverer implements DiscoveryListener {

	private final WiiRemoteFactory wiiRemotefactory = new WiiRemoteFactory();
	private final BlueCoveExceptionFactory exceptionFactory;

	private final CoronataButtonObserver buttonObserver;
	private final CoronataLifecycleEventsObserver leObserver;
	private final JobSynchronizer synchronizer;

	public WiiRemoteDiscoverer(BlueCoveExceptionFactory exceptionFactory,
			CoronataButtonObserver buttonObserver, final CoronataLifecycleEventsObserver leObserver,
			final Object monitor) {
		this.exceptionFactory = exceptionFactory;
		this.buttonObserver = buttonObserver;
		this.leObserver = leObserver;
		this.synchronizer = new JobSynchronizer(new Runnable() {
			@Override
			public void run() {
				leObserver.searchFinished();
				synchronized (monitor) {
					monitor.notify();
				}
			}
		});
	}

	@Override
	public void deviceDiscovered(final RemoteDevice device, final DeviceClass clazz) {

		// BlueCove documentation specifies that this method, deviceDiscovered,
		// should return immediately. For this reason and also to keep
		// search running, we handle device discoveries on separate
		// threads.

		synchronizer.addJob("DeviceDiscovered", new Runnable() {
			@Override
			public void run() {
				handleDeviceDiscovered(device, clazz);
			}
		});
	}

	@Override
	public void inquiryCompleted(int reason) {
		synchronizer.finishedAddingJobs();
	}

	private void handleDeviceDiscovered(RemoteDevice device, DeviceClass clazz) {
		String address = device.getBluetoothAddress();
		String deviceClass = ((Object) clazz).toString();

		leObserver.bluetoothDeviceFound(address, deviceClass);

		try {
			wiiRemotefactory.assertDeviceIsWiiRemote(device);
			leObserver.wiiRemoteIdentified();
			CoronataWiiRemote w = wiiRemotefactory.createWiiRemote(device, buttonObserver, leObserver);
			leObserver.connected(w);

		} catch (DeviceRejectedIdentification e) {
			leObserver.deviceRejectedIdentification(address, deviceClass);

		} catch (DeviceIdentifiedAsNotWiiRemote e) {
			leObserver.deviceIdentifiedAsNotWiiRemote(address, deviceClass);

		} catch (WiiRemoteRejectedConnection e) {
			leObserver.errorOccurred(exceptionFactory.wiiRemoteRejectedConnection(e.getCause()));
		}

	}

	@Override
	public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
	}

	@Override
	public void serviceSearchCompleted(int transID, int respCode) {
	}

}