package com.github.awvalenti.bauhinia.coronata;

public abstract class StateAbstractNeverRun extends State {

	@Override
	final boolean shouldRun(Countdown countdown, boolean stopRequested) {
		return false;
	}

	@Override
	final State run() {
		throw new UnsupportedOperationException(
				getClass().getSimpleName() + " should never run");
	}

}
