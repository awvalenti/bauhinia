package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;

class CompositeDisconnectionObserver implements CoronataDisconnectionObserver {

	private final List<CoronataDisconnectionObserver> listeners = new ArrayList<CoronataDisconnectionObserver>();

	public void add(CoronataDisconnectionObserver o) {
		listeners.add(o);
	}

	@Override
	public void disconnected() {
		for (CoronataDisconnectionObserver o : listeners) {
			o.disconnected();
		}
	}

}
