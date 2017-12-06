package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class ConnectionRejectedState extends State {

	private final StateFactory states;

	private final CoronataLifecycleEventsObserver leObserver;
	private final String btAddress;

	ConnectionRejectedState(StateFactory states,
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
