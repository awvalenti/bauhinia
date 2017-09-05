package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;

class CompositeDisconnectionObserver implements CoronataDisconnectionObserver {

	private final List<CoronataDisconnectionObserver> observers = new ArrayList<CoronataDisconnectionObserver>();

	public void add(CoronataDisconnectionObserver o) {
		observers.add(o);
	}

	@Override
	public void disconnected() {
		for (CoronataDisconnectionObserver o : observers) {
			o.disconnected();
		}
	}

}
