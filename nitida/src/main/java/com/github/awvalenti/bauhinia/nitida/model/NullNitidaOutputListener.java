package com.github.awvalenti.bauhinia.nitida.model;

import com.github.awvalenti.bauhinia.forficata.ForficataFailure;
import com.github.awvalenti.bauhinia.forficata.Phase;

public class NullNitidaOutputListener implements NitidaOutputListener {

	@Override
	public void run() {
	}

	@Override
	public void running(Phase phase) {
	}

	@Override
	public void success(Phase phase) {
	}

	@Override
	public void failure(Phase phase, ForficataFailure failure) {
	}

	@Override
	public void wiimoteDisconnected() {
	}

}
