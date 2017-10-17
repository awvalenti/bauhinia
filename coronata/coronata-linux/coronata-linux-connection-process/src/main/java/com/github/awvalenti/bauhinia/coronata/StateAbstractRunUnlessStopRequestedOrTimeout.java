package com.github.awvalenti.bauhinia.coronata;

public abstract class StateAbstractRunUnlessStopRequestedOrTimeout extends State {

	@Override
	final boolean shouldRun(Countdown countdown, boolean stopRequested) {
		return !stopRequested && !countdown.finished();
	}

}
