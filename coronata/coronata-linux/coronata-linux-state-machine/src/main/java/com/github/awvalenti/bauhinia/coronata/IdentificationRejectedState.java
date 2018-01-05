package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class IdentificationRejectedState extends State {

	private final StateFactory states;

	private final CoronataLifecycleEventsObserver leObserver;
	private final String btAddress;

	IdentificationRejectedState(StateFactory states,
			CoronataLifecycleEventsObserver leObserver, String btAddress) {
		this.states = states;
		this.leObserver = leObserver;
		this.btAddress = btAddress;
	}

	@Override
	State run() {
		leObserver.identificationRejected(btAddress);
		return states.identifyNextDevice();
	}

}
