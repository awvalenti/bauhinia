package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

class InquiryResult implements DiscoveryListener {

	private final CandidatesQueue candidateDevices = new CandidatesQueue();

	private volatile boolean finished;

	boolean isFinished() {
		return finished;
	}

	CandidatesQueue getCandidateDevices() {
		return candidateDevices;
	}

	@Override
	public void deviceDiscovered(RemoteDevice btDevice,
			DeviceClass deviceClass) {
		candidateDevices.push(new CandidateDevice(btDevice, deviceClass));
	}

	@Override
	public void inquiryCompleted(int discType) {
		finished = true;
	}

	@Override
	public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
	}

	@Override
	public void serviceSearchCompleted(int transID, int respCode) {
	}

}
