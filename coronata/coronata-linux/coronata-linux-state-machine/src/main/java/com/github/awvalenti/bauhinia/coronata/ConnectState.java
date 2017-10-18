package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.State.RunPolicy.*;

import com.github.awvalenti.bauhinia.coronata.WiiRemoteFactory.ConnectionRejected;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class ConnectState extends State {

	private final StateFactory states;

	private final CoronataLifecycleEventsObserver leObserver;
	private final String btAddress;
	private final WiiRemoteFactory wiiRemoteFactory;

	ConnectState(StateFactory states,
			CoronataLifecycleEventsObserver leObserver, String btAddress,
			WiiRemoteFactory wiiRemoteFactory) {
		super(STOP_ONLY_IF_REQUESTED);
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
