package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.State.RunPolicy.*;

class FinishState extends State {

	FinishState() {
		super(NEVER_RUN);
	}

	@Override
	State run() {
		throw new UnsupportedOperationException("Should not have been called");
	}

}
