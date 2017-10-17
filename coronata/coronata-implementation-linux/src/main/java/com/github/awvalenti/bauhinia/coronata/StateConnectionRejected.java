package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class StateConnectionRejected extends StateAbstractAlwaysRun {

	private final StateFactory states;

	private final CoronataLifecycleEventsObserver leObserver;
	private final String btAddress;

	StateConnectionRejected(StateFactory states,
			CoronataLifecycleEventsObserver leObserver, String btAddress) {
		this.states = states;
		this.btAddress = btAddress;
		this.leObserver = leObserver;
	}

	@Override
	State run() {
		leObserver.connectionRejected(btAddress);
		return states.identifyNextDevice();
	}

}
