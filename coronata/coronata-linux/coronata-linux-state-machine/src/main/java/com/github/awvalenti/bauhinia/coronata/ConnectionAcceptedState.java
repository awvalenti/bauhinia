package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class ConnectionAcceptedState extends State {

	private final StateFactory states;

	private final CoronataLifecycleEventsObserver leObserver;
	private final Counter connectionsCounter;
	private final WiiRemoteConnection connection;
	private final CoronataButtonObserver buttonObserver;

	ConnectionAcceptedState(StateFactory states,
			CoronataLifecycleEventsObserver leObserver,
			Counter connectionsCounter, WiiRemoteConnection connection,
			CoronataButtonObserver buttonObserver) {
		this.states = states;
		this.leObserver = leObserver;
		this.connectionsCounter = connectionsCounter;
		this.connection = connection;
		this.buttonObserver = buttonObserver;
	}

	@Override
	boolean shouldStopNow(boolean stopRequested, TimeoutCoundown timeout) {
		return false;
	}

	@Override
	State run() {
		CoronataWiiRemote wiiRemote =
				new BlueCoveWiiRemote(connection, buttonObserver, leObserver);

		leObserver.connected(wiiRemote);

		connectionsCounter.increment();

		return connectionsCounter.reachedGoal() ? states.finish() :
				states.identifyNextDevice();
	}

}
