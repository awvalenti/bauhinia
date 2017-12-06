package com.github.awvalenti.bauhinia.coronata;

/**
 * The end of the state machine. When the machine gets here, this state is run
 * once and then the machine stops. This ensures that no cleanup method from
 * other state is executed.
 */
class FinishState extends State {

	private boolean hasAlreadyRun = false;

	@Override
	boolean shouldStopNow(boolean stopRequested, TimeoutCoundown timeout) {
		return hasAlreadyRun;
	}

	@Override
	State run() {
		hasAlreadyRun = true;
		return this;
	}

	@Override
	void cleanUpIfStoppedHere() {
	}

}
