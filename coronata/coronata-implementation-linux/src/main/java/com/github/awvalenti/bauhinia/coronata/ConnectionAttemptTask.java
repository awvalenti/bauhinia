package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.RemoteDevice;

import com.github.awvalenti.bauhinia.coronata.WiiRemoteFactory.IdentifiedAsNonWiiRemote;
import com.github.awvalenti.bauhinia.coronata.WiiRemoteFactory.IdentificationRejected;
import com.github.awvalenti.bauhinia.coronata.WiiRemoteFactory.ConnectionRejected;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class ConnectionAttemptTask implements Comparable<ConnectionAttemptTask> {

	private final RemoteDevice device;
	private final DeviceClass clazz;

	private final CoronataLifecycleEventsObserver leObserver;
	private final CoronataButtonObserver buttonObserver;
	private final WiiRemoteFactory wiiRemotefactory;

	public ConnectionAttemptTask(RemoteDevice device, DeviceClass clazz,
			CoronataLifecycleEventsObserver leObserver,
			CoronataButtonObserver buttonObserver,
			WiiRemoteFactory wiiRemoteFactory) {
		this.device = device;
		this.clazz = clazz;
		this.leObserver = leObserver;
		this.buttonObserver = buttonObserver;
		this.wiiRemotefactory = wiiRemoteFactory;
	}

	enum ConnectionResult {
		SUCCESS, FAILURE
	}

	public ConnectionResult run() {
		final String address = device.getBluetoothAddress();

		try {
			wiiRemotefactory.assertDeviceIsWiiRemote(device);

			leObserver.identifiedAsWiiRemote(address);

			CoronataWiiRemote w = wiiRemotefactory.create(address,
					buttonObserver, leObserver);
			leObserver.connected(w);

			return ConnectionResult.SUCCESS;

		} catch (IdentificationRejected e) {
			leObserver.identificationRejected(address);

		} catch (IdentifiedAsNonWiiRemote e) {
			leObserver.identifiedAsNonWiiRemote(address);

		} catch (ConnectionRejected e) {
			leObserver.connectionRejected(address);
		}

		return ConnectionResult.FAILURE;
	}

	@Override
	public final int compareTo(ConnectionAttemptTask o) {
		return Integer.compare(getPriority(), o.getPriority());
	}

	private int getPriority() {
		int wiiRemoteProbability = 0;

		// http://wiibrew.org/wiki/Wiimote#SDP_information

		boolean isPeripheral = clazz.getMajorDeviceClass() == 1280;
		if (isPeripheral) wiiRemoteProbability += 25;

		boolean isJoystick = clazz.getMinorDeviceClass() == 4;
		if (isJoystick) wiiRemoteProbability += 50;

		return wiiRemoteProbability;
	}

}
