package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import javax.bluetooth.RemoteDevice;

class IdentifyNextDeviceState extends State {

	private final StateFactory states;

	private final CandidatesQueue candidates;

	IdentifyNextDeviceState(StateFactory states, CandidatesQueue candidates) {
		this.states = states;
		this.candidates = candidates;
	}

	@Override
	State run() {
		if (candidates.isEmpty()) return states.startInquiry();

		CandidateDevice current = candidates.pop();
		RemoteDevice btDevice = current.btDevice;
		String btAddress = btDevice.getBluetoothAddress();

		final String name;
		try {
			name = btDevice.getFriendlyName(false);
		} catch (IOException e) {
			return states.identificationRejected(btAddress);
		}

		if (!isWiiRemote(name)) {
			return states.identifiedAsNonWiiRemote(btAddress);
		}

		return states.openControlPipe(btAddress);
	}

	private boolean isWiiRemote(String name) {
		return name != null && name.startsWith("Nintendo RVL-CNT-01");
	}

}
