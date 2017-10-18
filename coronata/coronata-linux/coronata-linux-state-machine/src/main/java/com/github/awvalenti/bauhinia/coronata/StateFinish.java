package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.State.RunPolicy.*;

class StateFinish extends State {

	StateFinish() {
		super(NEVER_RUN);
	}

	@Override
	State run() {
		throw new UnsupportedOperationException("Should not have been called");
	}

}
