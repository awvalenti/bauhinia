package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.RemoteDevice;

class CandidateDevice implements Comparable<CandidateDevice> {

	final RemoteDevice btDevice;
	final DeviceClass clazz;

	CandidateDevice(RemoteDevice btDevice, DeviceClass clazz) {
		this.btDevice = btDevice;
		this.clazz = clazz;
	}

	@Override
	public final int compareTo(CandidateDevice o) {
		return Integer.compare(getPriority(), o.getPriority());
	}

	private int getPriority() {
		int wiiRemoteProbability = 0;

		boolean isPeripheral = clazz.getMajorDeviceClass() == 1280,
				
				// Nintendo RVL-CNT-01
				isJoystick = clazz.getMinorDeviceClass() == 4,
				
				// Nintendo RVL-CNT-01-TR
				isGamepad = clazz.getMinorDeviceClass() == 8;
		
		if (isPeripheral) wiiRemoteProbability += 25;
		if (isJoystick || isGamepad) wiiRemoteProbability += 50;

		return wiiRemoteProbability;
	}

}
