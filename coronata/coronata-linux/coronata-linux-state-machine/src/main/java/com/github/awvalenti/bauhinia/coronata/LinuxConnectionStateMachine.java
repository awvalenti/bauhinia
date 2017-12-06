package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class LinuxConnectionStateMachine {

	private final CoronataLifecycleEventsObserver leObserver;
	private final int timeoutInSeconds;

	private final StateFactory states;

	private volatile boolean stopRequested = false;

	LinuxConnectionStateMachine(CoronataLifecycleEventsObserver leObserver,
			int timeoutInSeconds, int numberOfWiiRemotes,
			CoronataButtonObserver buttonObserver) {
		this.leObserver = leObserver;
		this.timeoutInSeconds = timeoutInSeconds;
		states = new StateFactory(leObserver, buttonObserver,
				new Counter(numberOfWiiRemotes));
	}

	void run() {
		TimeoutCoundown timeoutCountdown =
				new TimeoutCoundown(timeoutInSeconds);

		leObserver.coronataStarted();

		State current = states.loadLibrary();
		for (;;) {
			State next = current.run();
			if (next.shouldStopNow(stopRequested, timeoutCountdown)) {
				current.cleanUpIfStoppedHere();
				break;
			}
			current = next;
		}

		leObserver.searchFinished();
	}

	void requestStop() {
		stopRequested = true;
	}

}
