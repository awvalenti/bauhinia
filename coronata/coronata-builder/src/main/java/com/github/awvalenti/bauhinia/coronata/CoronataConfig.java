package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataErrorObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;

class CoronataConfig implements ReadableCoronataConfig {

	private final ObserversAggregation observers = new ObserversAggregation();
	private final EventsMediator mediator = new EventsMediator(observers);

	private int wiiRemotesExpected = 1;
	private int minimumTimeoutInSeconds = 30;


	@Override
	public int getWiiRemotesExpected() {
		return wiiRemotesExpected;
	}

	public void setWiiRemotesExpected(int wiiRemotesExpected) {
		this.wiiRemotesExpected = wiiRemotesExpected;
	}

	@Override
	public int getMinimumTimeoutInSeconds() {
		return minimumTimeoutInSeconds;
	}

	public void setMinimumTimeoutInSeconds(int minimumTimeoutInSeconds) {
		this.minimumTimeoutInSeconds = minimumTimeoutInSeconds;
	}

	@Override
	public CoronataButtonObserver getButtonObserver() {
		return mediator;
	}

	@Override
	public CoronataLifecycleEventsObserver getLifecycleEventsObserver() {
		return mediator;
	}

	public void addObserver(CoronataButtonObserver o) {
		observers.button.add(o);
	}

	public void addObserver(CoronataConnectionObserver o) {
		observers.connection.add(o);
	}

	public void addObserver(CoronataDisconnectionObserver o) {
		observers.disconnection.add(o);
	}

	public void addObserver(CoronataLifecycleEventsObserver o) {
		observers.lifecycleEvents.add(o);
	}

	public void addObserver(CoronataLifecycleStateObserver o) {
		// XXX Should not have to call this here; calling this triggers the
		// configuration of initial state on observer. But the observer
		// should configure itself upon construction instead of depending
		// on this method being called.
		o.enteredIdleState();

		observers.lifecycleState.add(o);
	}

	public void addObserver(CoronataPhaseObserver o) {
		observers.phase.add(o);
	}

	public void addObserver(CoronataErrorObserver o) {
		observers.error.add(o);
	}

}
