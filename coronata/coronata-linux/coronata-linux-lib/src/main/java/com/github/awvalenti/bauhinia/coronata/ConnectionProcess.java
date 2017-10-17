package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.State;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class ConnectionProcess {

	private final CoronataLifecycleEventsObserver leObserver;
	private final int timeout;

	private final StateFactory states;

	private volatile boolean stopRequested = false;

	ConnectionProcess(CoronataLifecycleEventsObserver leObserver, int timeout,
			int numberOfWiiRemotes, WiiRemoteFactory wiiRemoteFactory) {
		this.leObserver = leObserver;
		this.timeout = timeout;
		this.states = new StateFactory(leObserver, wiiRemoteFactory,
				new Counter(numberOfWiiRemotes));
	}

	void runSynchronously() {
		Countdown countdown = new Countdown(timeout);

		leObserver.coronataStarted();

		State current = states.loadLibrary();
		while (current.shouldRun(countdown, stopRequested)) {
			current = current.run();
		}
		current.cleanUpIfDidntRun();

		leObserver.searchFinished();
	}

	void requestStop() {
		stopRequested = true;
	}

}
