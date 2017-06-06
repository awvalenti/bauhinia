package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.BlueCoveConnector;
import com.github.awvalenti.bauhinia.coronata.CoronataConnector;
import com.github.awvalenti.bauhinia.coronata.WiiuseJConnector;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteButtonListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiiRemoteConnectionObserver;

// TODO Merge with CoronataConfig class
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
	public CoronataBuilderStep3 oneWiiRemote() {
		config.setWiiRemotesExpected(1);
		return this;
	}

	@Override
	public CoronataBuilderStep3 buttonListener(WiiRemoteButtonListener listener) {
		config.addButtonListener(listener);
		return this;
	}

	@Override
	public CoronataBuilderStep3 wiiRemoteConnectionObserver(CoronataWiiRemoteConnectionObserver o) {
		config.addConnectionObserver(o);
		return this;
	}

	@Override
	public CoronataBuilderStep3 fullObserver(CoronataFullObserver o) {
		config.addFullObserver(o);
		return this;
	}

	@Override
	public CoronataBuilderStep3 phaseStateObserver(CoronataPhaseObserver o) {
		config.addPhaseStateObserver(o);
		return this;
	}

	@Override
	public CoronataBuilderStep3 connectionStateObserver(CoronataConnectionStateObserver o) {
		config.addConnectionStateObserver(o);
		return this;
	}

	@Override
	public CoronataConnector build() {
		return isWindows() ? new WiiuseJConnector(config) : new BlueCoveConnector(
				config);
	}

	private static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

}
