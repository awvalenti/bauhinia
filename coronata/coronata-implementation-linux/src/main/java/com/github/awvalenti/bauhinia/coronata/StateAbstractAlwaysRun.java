package com.github.awvalenti.bauhinia.coronata;

public abstract class StateAbstractAlwaysRun extends State {

	@Override
	final boolean shouldRun(Countdown countdown, boolean stopRequested) {
		return true;
	}

}
