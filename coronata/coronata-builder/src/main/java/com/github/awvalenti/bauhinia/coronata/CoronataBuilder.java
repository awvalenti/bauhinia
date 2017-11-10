package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataErrorObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;

public class CoronataBuilder {

	private final CoronataConfig config = new CoronataConfig();

	private CoronataBuilder() {
	}

	public static CoronataBuilder beginConfig() {
		return new CoronataBuilder();
	}

	public CoronataBuilder wiiRemotesExpected(int count) {
		config.setNumberOfWiiRemotes(count);
		return this;
	}

	public CoronataBuilder minimumTimeoutInSeconds(int timeout) {
		config.setTimeoutInSeconds(timeout);
		return this;
	}

	public CoronataBuilder onButton(CoronataButtonObserver o) {
		config.addObserver(o);
		return this;
	}

	public CoronataBuilder onConnection(CoronataConnectionObserver o) {
		config.addObserver(o);
		return this;
	}

	public CoronataBuilder onDisconnection(CoronataDisconnectionObserver o) {
		config.addObserver(o);
		return this;
	}

	public CoronataBuilder onLifecycleEvents(CoronataLifecycleEventsObserver o) {
		config.addObserver(o);
		return this;
	}

	public CoronataBuilder onLifecycleState(CoronataLifecycleStateObserver o) {
		config.addObserver(o);
		return this;
	}

	public CoronataBuilder onPhase(CoronataPhaseObserver o) {
		config.addObserver(o);
		return this;
	}

	public CoronataBuilder onError(CoronataErrorObserver o) {
		config.addObserver(o);
		return this;
	}

	public CoronataConnectionProcess endConfig() {
		boolean isWindows = System.getProperty("os.name").toLowerCase().contains("win");
		return isWindows ? new CoronataWindows(config) : new CoronataLinux(config);
	}

}
