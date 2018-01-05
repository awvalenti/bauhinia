package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleStateObserver;

class CompositeLifecycleStateObserver implements
		CoronataLifecycleStateObserver {

	private final List<CoronataLifecycleStateObserver> observers =
			new ArrayList<CoronataLifecycleStateObserver>();

	public void add(CoronataLifecycleStateObserver o) {
		observers.add(o);
	}

	@Override
	public void enteredIdleState() {
		for (CoronataLifecycleStateObserver o : observers) {
			o.enteredIdleState();
		}
	}

	@Override
	public void enteredInProcessState() {
		for (CoronataLifecycleStateObserver o : observers) {
			o.enteredInProcessState();
		}
	}

	@Override
	public void enteredConnectedState() {
		for (CoronataLifecycleStateObserver o : observers) {
			o.enteredConnectedState();
		}
	}

}
