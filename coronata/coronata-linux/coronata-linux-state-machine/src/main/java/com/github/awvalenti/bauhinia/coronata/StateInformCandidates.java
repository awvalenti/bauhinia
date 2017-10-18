package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.State.RunPolicy.*;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class StateInformCandidates extends State {

	private final StateFactory states;

	private final CoronataLifecycleEventsObserver leObserver;
	private final CandidatesQueue candidates;

	StateInformCandidates(StateFactory states,
			CoronataLifecycleEventsObserver leObserver, CandidatesQueue candidates) {
		super(STOP_IF_REQUESTED_OR_TIMEOUT);
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
