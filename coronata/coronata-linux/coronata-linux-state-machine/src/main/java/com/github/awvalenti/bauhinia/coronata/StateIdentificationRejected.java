package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.State.RunPolicy.*;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class StateIdentificationRejected extends State {

	private final StateFactory states;

	private final CoronataLifecycleEventsObserver leObserver;
	private final String btAddress;

	StateIdentificationRejected(
			StateFactory states, CoronataLifecycleEventsObserver leObserver, String btAddress) {
		super(STOP_IF_REQUESTED_OR_TIMEOUT);
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
