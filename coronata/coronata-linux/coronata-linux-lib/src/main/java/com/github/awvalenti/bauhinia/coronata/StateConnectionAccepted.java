package com.github.awvalenti.bauhinia.coronata;

class StateConnectionAccepted extends StateAbstractRunUnlessStopRequested {

	private final StateFactory states;

	private final BlueCoveWiiRemote wiiRemote;
	private final Counter connectionsCounter;

	StateConnectionAccepted(StateFactory states, Counter connectionsCounter,
			BlueCoveWiiRemote wiiRemote) {
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
