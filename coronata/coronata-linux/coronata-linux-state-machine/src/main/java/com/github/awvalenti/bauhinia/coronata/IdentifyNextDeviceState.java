package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.State.RunPolicy.*;

import java.io.IOException;

import javax.bluetooth.RemoteDevice;

class IdentifyNextDeviceState extends State {

	private final StateFactory states;

	private final CandidatesQueue candidates;

	IdentifyNextDeviceState(StateFactory states, CandidatesQueue candidates) {
		super(STOP_IF_REQUESTED_OR_TIMEOUT);
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

		return states.connect(btAddress);
	}

	private boolean isWiiRemote(String name) {
		return name != null && name.startsWith("Nintendo RVL-CNT-01");
	}

}
