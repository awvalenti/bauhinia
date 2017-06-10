package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionStateObserver;

class CompositeConnectionStateObserver implements CoronataConnectionStateObserver {

	private final List<CoronataConnectionStateObserver> observers = new ArrayList<CoronataConnectionStateObserver>();

	public void add(CoronataConnectionStateObserver o) {
		observers.add(o);
	}

	@Override
	public void enteredIdleState() {
		for (CoronataConnectionStateObserver o : observers) {
			o.enteredIdleState();
		}
	}

	@Override
	public void enteredInProcessState() {
		for (CoronataConnectionStateObserver o : observers) {
			o.enteredInProcessState();
		}
	}

	@Override
	public void enteredConnectedState() {
		for (CoronataConnectionStateObserver o : observers) {
			o.enteredConnectedState();
		}
	}

}
