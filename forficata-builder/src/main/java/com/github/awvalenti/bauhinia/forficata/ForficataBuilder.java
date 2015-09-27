package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.observers.ForficataConnectionStateObserver;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataPhaseObserver;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataWiimoteConnectionObserver;

class ForficataBuilder implements ForficataBuilderStep1, ForficataBuilderStep2,
		ForficataBuilderStep3 {

	private ForficataConfig config = new ForficataConfig();

	@Override
	public ForficataBuilderStep2 synchronousConnector() {
		config.setSynchronous(true);
		return this;
	}

	@Override
	public ForficataBuilderStep2 asynchronousConnector() {
		config.setSynchronous(false);
		return this;
	}

	@Override
	public ForficataBuilderStep3 oneWiimote(ForficataWiimoteListener listener) {
		config.setWiimotesExpected(1);
		config.setWiimoteListener(listener);
		return this;
	}

	@Override
	public ForficataBuilderStep3 wiimoteConnectionObserver(ForficataWiimoteConnectionObserver o) {
		config.addObserver(new WiimoteConnectionObserverAdapter(o));
		return this;
	}

	@Override
	public ForficataBuilderStep3 fullObserver(ForficataObserver o) {
		config.addObserver(o);
		return this;
	}

	@Override
	public ForficataBuilderStep3 phaseStateObserver(ForficataPhaseObserver o) {
		config.addObserver(new PhaseObserverAdapter(o));
		return this;
	}

	@Override
	public ForficataBuilderStep3 observers(ForficataObserver... observers) {
		for (ForficataObserver o : observers) {
			config.addObserver(o);
		}
		return this;
	}

	@Override
	public ForficataBuilderStep3 connectionStateObserver(ForficataConnectionStateObserver o) {
		config.addObserver(new ConnectionStateObserverAdapter(o));
		return this;
	}

	@Override
	public WiimoteConnector build() {
		return isWindows() ? new WiiuseJWiimoteConnector(config) : new BlueCoveWiimoteConnector(
				config);
	}

	private static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

}
