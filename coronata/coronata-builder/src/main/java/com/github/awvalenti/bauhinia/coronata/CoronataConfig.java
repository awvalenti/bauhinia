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

	private int numberOfWiiRemotes = 1;
	private int timeoutInSeconds = 40;

	@Override
	public int getNumberOfWiiRemotes() {
		return numberOfWiiRemotes;
	}

	public void setNumberOfWiiRemotes(int numberOfWiiRemotes) {
		this.numberOfWiiRemotes = numberOfWiiRemotes;
	}

	@Override
	public int getTimeoutInSeconds() {
		return timeoutInSeconds;
	}

	public void setTimeoutInSeconds(int timeoutInSeconds) {
		this.timeoutInSeconds = timeoutInSeconds;
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
		observers.lifecycleState.add(o);
	}

	public void addObserver(CoronataPhaseObserver o) {
		observers.phase.add(o);
	}

	public void addObserver(CoronataErrorObserver o) {
		observers.error.add(o);
	}

}
