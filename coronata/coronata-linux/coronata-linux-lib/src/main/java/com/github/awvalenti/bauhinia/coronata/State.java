package com.github.awvalenti.bauhinia.coronata;

abstract class State {

	abstract boolean shouldRun(Countdown countdown, boolean stopRequested);

	abstract State run();

	void cleanUpIfDidntRun() {
	}

}
