package com.github.awvalenti.bauhinia.coronata;

abstract class State {

	enum RunPolicy {
		ALWAYS_RUN {
			@Override
			boolean shouldRun(Countdown countdown, boolean stopRequested) {
				return true;
			}
		},

		STOP_ONLY_IF_REQUESTED {
			@Override
			boolean shouldRun(Countdown countdown, boolean stopRequested) {
				return !stopRequested;
			}
		},

		STOP_IF_REQUESTED_OR_TIMEOUT {
			@Override
			boolean shouldRun(Countdown countdown, boolean stopRequested) {
				return !stopRequested && !countdown.finished();
			}
		},

		NEVER_RUN {
			@Override
			boolean shouldRun(Countdown countdown, boolean stopRequested) {
				return false;
			}
		},

		;

		abstract boolean shouldRun(Countdown countdown, boolean stopRequested);
	}

	private final RunPolicy runPolicy;

	State(RunPolicy runPolicy) {
		this.runPolicy = runPolicy;
	}

	final boolean shouldRun(Countdown countdown, boolean stopRequested) {
		return runPolicy.shouldRun(countdown, stopRequested);
	}

	abstract State run();

	void cleanUpIfDidntRun() {
	}

}
