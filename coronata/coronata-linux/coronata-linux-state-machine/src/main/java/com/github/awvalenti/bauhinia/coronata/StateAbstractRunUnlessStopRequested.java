package com.github.awvalenti.bauhinia.coronata;

public abstract class StateAbstractRunUnlessStopRequested extends State {

	@Override
	final boolean shouldRun(Countdown countdown, boolean stopRequested) {
		return !stopRequested;
	}

}
