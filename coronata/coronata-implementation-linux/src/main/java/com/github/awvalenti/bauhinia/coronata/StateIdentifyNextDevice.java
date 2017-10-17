package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.RemoteDevice;

import com.github.awvalenti.bauhinia.coronata.WiiRemoteFactory.IdentificationRejected;
import com.github.awvalenti.bauhinia.coronata.WiiRemoteFactory.IdentifiedAsNonWiiRemote;

class StateIdentifyNextDevice extends StateAbstractRunUnlessStopRequestedOrTimeout {

	private final StateFactory states;

	private final CandidatesQueue candidates;
	private final WiiRemoteFactory wiiRemoteFactory;

	StateIdentifyNextDevice(StateFactory states, CandidatesQueue candidates,
			WiiRemoteFactory wiiRemoteFactory) {
		this.states = states;
		this.candidates = candidates;
		this.wiiRemoteFactory = wiiRemoteFactory;
	}

	@Override
	State run() {
		if (candidates.isEmpty()) return states.startInquiry();

		CandidateDevice current = candidates.pop();
		RemoteDevice btDevice = current.btDevice;
		String btAddress = btDevice.getBluetoothAddress();

		// TODO Add a new event: leObserver.identifying(btAddress);
		// This will eliminate the need for moveToPhase(FIND_WII_REMOTE)
		// on EventsMediator.connectionRejected.

		try {
			// TODO Add a new event: leObserver.nameOfTheDevice(name);
			wiiRemoteFactory.assertDeviceIsWiiRemote(btDevice);
			return states.connect(btAddress);

		} catch (IdentificationRejected e) {
			return states.identificationRejected(btAddress);

		} catch (IdentifiedAsNonWiiRemote e) {
			return states.identifiedAsNonWiiRemote(btAddress);
		}
	}

}
