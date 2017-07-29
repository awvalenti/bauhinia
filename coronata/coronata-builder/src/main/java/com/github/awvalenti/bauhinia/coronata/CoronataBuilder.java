package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.Coronata;
import com.github.awvalenti.bauhinia.coronata.CoronataLinux;
import com.github.awvalenti.bauhinia.coronata.CoronataWindows;
import com.github.awvalenti.bauhinia.coronata.buildersteps.CoronataBuilderStep1;
import com.github.awvalenti.bauhinia.coronata.buildersteps.CoronataBuilderStep2;
import com.github.awvalenti.bauhinia.coronata.buildersteps.CoronataBuilderStep3;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;

public class CoronataBuilder implements CoronataBuilderStep1, CoronataBuilderStep2,
		CoronataBuilderStep3 {

	private final CoronataConfig config = new CoronataConfig();

	private CoronataBuilder() {
	}

	public static CoronataBuilderStep1 beginConfig() {
		return new CoronataBuilder();
	}

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
	public CoronataBuilderStep3 onButton(CoronataButtonObserver listener) {
		config.addObserver(listener);
		return this;
	}

	@Override
	public CoronataBuilderStep3 onConnection(CoronataConnectionObserver o) {
		config.addObserver(o);
		return this;
	}

	@Override
	public CoronataBuilderStep3 onDisconnection(CoronataDisconnectionObserver l) {
		config.addObserver(l);
		return this;
	}

	@Override
	public CoronataBuilderStep3 onLifecycleEvents(CoronataLifecycleEventsObserver o) {
		config.addObserver(o);
		return this;
	}

	@Override
	public CoronataBuilderStep3 onLifecycleState(CoronataLifecycleStateObserver o) {
		config.addObserver(o);
		return this;
	}

	@Override
	public CoronataBuilderStep3 onPhase(CoronataPhaseObserver o) {
		config.addObserver(o);
		return this;
	}

	@Override
	public Coronata endConfig() {
		boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
		return isWindows ? new CoronataWindows(config) : new CoronataLinux(config);
	}

}
