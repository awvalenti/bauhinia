package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.WiiRemoteFactory.ConnectionRejected;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class StateConnect extends StateAbstractRunUnlessStopRequested {

	private final StateFactory states;

	private final CoronataLifecycleEventsObserver leObserver;
	private final String btAddress;
	private final WiiRemoteFactory wiiRemoteFactory;

	StateConnect(StateFactory states,
			CoronataLifecycleEventsObserver leObserver, String btAddress,
			WiiRemoteFactory wiiRemoteFactory) {
		this.states = states;
		this.leObserver = leObserver;
		this.btAddress = btAddress;
		this.wiiRemoteFactory = wiiRemoteFactory;
	}

	@Override
	State run() {
		leObserver.identifiedAsWiiRemote(btAddress);

		try {
			BlueCoveWiiRemote wiiRemote = wiiRemoteFactory.create(btAddress);
			leObserver.connected(wiiRemote);
			return states.connectionAccepted(wiiRemote);

		} catch (ConnectionRejected e) {
			return states.connectionRejected(btAddress);
		}
	}

}
