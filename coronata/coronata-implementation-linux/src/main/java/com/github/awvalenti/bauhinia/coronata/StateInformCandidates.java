package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class StateInformCandidates extends StateAbstractRunUnlessStopRequestedOrTimeout {

	private final StateFactory states;

	private final CoronataLifecycleEventsObserver leObserver;
	private final CandidatesQueue candidates;

	StateInformCandidates(StateFactory states,
			CoronataLifecycleEventsObserver leObserver, CandidatesQueue candidates) {
		this.states = states;
		this.leObserver = leObserver;
		this.candidates = candidates;
	}

	@Override
	State run() {
		for (CandidateDevice c : candidates) {
			leObserver.bluetoothDeviceFound(c.btDevice.getBluetoothAddress(),
					((Object) c.clazz).toString());
		}
		return states.identifyNextDevice();
	}

}
