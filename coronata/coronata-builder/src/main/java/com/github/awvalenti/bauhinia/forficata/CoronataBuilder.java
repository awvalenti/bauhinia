package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.listeners.CoronataButtonListener;
import com.github.awvalenti.bauhinia.forficata.observers.CoronataConnectionStateObserver;
import com.github.awvalenti.bauhinia.forficata.observers.CoronataObserver;
import com.github.awvalenti.bauhinia.forficata.observers.CoronataPhaseObserver;
import com.github.awvalenti.bauhinia.forficata.observers.CoronataWiimoteConnectionObserver;

class CoronataBuilder implements CoronataBuilderStep1, CoronataBuilderStep2,
		CoronataBuilderStep3 {

	private final CoronataConfig config = new CoronataConfig();

	@Override
	public CoronataBuilderStep2 synchronous() {
		config.setSynchronous(true);
		return this;
	}

	@Override
	public CoronataBuilderStep2 asynchronous() {
		config.setSynchronous(false);
		return this;
	}

	@Override
	public CoronataBuilderStep3 oneWiimote() {
		config.setWiimotesExpected(1);
		return this;
	}

	@Override
	public CoronataBuilderStep3 buttonListener(CoronataButtonListener listener) {
		config.addButtonListener(new ButtonListenerAdapter(listener));
		return this;
	}

	@Override
	public CoronataBuilderStep3 wiimoteConnectionObserver(CoronataWiimoteConnectionObserver o) {
		config.addObserver(new WiimoteConnectionObserverAdapter(o));
		return this;
	}

	@Override
	public CoronataBuilderStep3 fullObserver(CoronataObserver o) {
		config.addObserver(o);
		return this;
	}

	@Override
	public CoronataBuilderStep3 phaseStateObserver(CoronataPhaseObserver o) {
		config.addObserver(new PhaseObserverAdapter(o));
		return this;
	}

	@Override
	public CoronataBuilderStep3 observers(CoronataObserver... observers) {
		for (CoronataObserver o : observers) {
			config.addObserver(o);
		}
		return this;
	}

	@Override
	public CoronataBuilderStep3 connectionStateObserver(CoronataConnectionStateObserver o) {
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
