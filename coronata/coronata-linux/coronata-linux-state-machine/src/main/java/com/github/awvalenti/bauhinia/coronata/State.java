package com.github.awvalenti.bauhinia.coronata;

abstract class State {

	boolean shouldStopNow(boolean stopRequested, TimeoutCoundown timeout) {
		return stopRequested || timeout.finished();
	}

	abstract State run();

	void cleanUpIfStoppedHere() {
	}

}
