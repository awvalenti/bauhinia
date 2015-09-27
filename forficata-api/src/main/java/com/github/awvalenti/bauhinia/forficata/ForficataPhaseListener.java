package com.github.awvalenti.bauhinia.forficata;

// TODO Divide in more listeners. One should contain only wiimoteConnected.

public interface ForficataPhaseListener {

	void running(Phase phase);

	void success(Phase phase);

	void failure(Phase phase, ForficataFailure failure);

	void wiimoteConnected(Wiimote wiimote);

}
