package com.github.awvalenti.bauhinia.coronata;

abstract class State {

	enum RunPolicy {
		ALWAYS_RUN {
			@Override
			boolean shouldRun(boolean stopRequested, Countdown countdown) {
				return true;
			}
		},

		STOP_ONLY_IF_REQUESTED {
			@Override
			boolean shouldRun(boolean stopRequested, Countdown countdown) {
				return !stopRequested;
			}
		},

		STOP_IF_REQUESTED_OR_TIMEOUT {
			@Override
			boolean shouldRun(boolean stopRequested, Countdown countdown) {
				return !stopRequested && !countdown.finished();
			}
		},

		NEVER_RUN {
			@Override
			boolean shouldRun(boolean stopRequested, Countdown countdown) {
				return false;
			}
		},

		;

		abstract boolean shouldRun(boolean stopRequested, Countdown countdown);
	}

	private final RunPolicy runPolicy;

	State(RunPolicy runPolicy) {
		this.runPolicy = runPolicy;
	}

	final boolean shouldRun(boolean stopRequested, Countdown countdown) {
		return runPolicy.shouldRun(stopRequested, countdown);
	}

	abstract State run();

	void cleanUpIfDidntRun() {
	}

}
