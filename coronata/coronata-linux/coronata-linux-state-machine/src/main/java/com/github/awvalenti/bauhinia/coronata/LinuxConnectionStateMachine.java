package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class LinuxConnectionStateMachine {

	private final CoronataLifecycleEventsObserver leObserver;
	private final int timeout;

	private final StateFactory states;

	private volatile boolean stopRequested = false;

	LinuxConnectionStateMachine(CoronataLifecycleEventsObserver leObserver, int timeout,
			int numberOfWiiRemotes, CoronataButtonObserver buttonObserver) {
		this.leObserver = leObserver;
		this.timeout = timeout;
		this.states = new StateFactory(leObserver,
				new WiiRemoteFactory(buttonObserver, leObserver),
				new Counter(numberOfWiiRemotes));
	}

	void runSynchronously() {
		Countdown countdown = new Countdown(timeout);

		leObserver.coronataStarted();

		State current = states.loadLibrary();
		while (current.shouldRun(stopRequested, countdown)) {
			current = current.run();
		}
		current.cleanUpIfDidntRun();

		leObserver.searchFinished();
	}

	void requestStop() {
		stopRequested = true;
	}

}
