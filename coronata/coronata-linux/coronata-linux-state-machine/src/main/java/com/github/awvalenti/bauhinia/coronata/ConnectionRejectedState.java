package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.State.RunPolicy.*;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class ConnectionRejectedState extends State {

	private final StateFactory states;

	private final CoronataLifecycleEventsObserver leObserver;
	private final String btAddress;

	ConnectionRejectedState(StateFactory states,
			CoronataLifecycleEventsObserver leObserver, String btAddress) {
		super(ALWAYS_RUN);
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
