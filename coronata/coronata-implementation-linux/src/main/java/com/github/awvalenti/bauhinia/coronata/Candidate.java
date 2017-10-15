package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.RemoteDevice;

class Candidate implements Comparable<Candidate> {

	final RemoteDevice btDevice;
	final DeviceClass clazz;

	Candidate(RemoteDevice btDevice, DeviceClass clazz) {
		this.btDevice = btDevice;
		this.clazz = clazz;
	}

	@Override
	public final int compareTo(Candidate o) {
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
