package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.State.RunPolicy.*;

class ConnectionAcceptedState extends State {

	private final StateFactory states;

	private final BlueCoveWiiRemote wiiRemote;
	private final Counter connectionsCounter;

	ConnectionAcceptedState(StateFactory states, Counter connectionsCounter,
			BlueCoveWiiRemote wiiRemote) {
		super(STOP_ONLY_IF_REQUESTED);
		this.states = states;
		this.connectionsCounter = connectionsCounter;
		this.wiiRemote = wiiRemote;
	}

	@Override
	void cleanUpIfDidntRun() {
		wiiRemote.disconnect();
	}

	@Override
	State run() {
		connectionsCounter.increment();

		return connectionsCounter.reachedGoal() ? states.finish() :
				states.identifyNextDevice();
	}

}
