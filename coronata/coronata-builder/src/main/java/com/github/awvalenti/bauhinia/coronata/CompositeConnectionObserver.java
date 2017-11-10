package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;

class CompositeConnectionObserver implements CoronataConnectionObserver {

	private final List<CoronataConnectionObserver> observers =
			new ArrayList<CoronataConnectionObserver>();

	public void add(CoronataConnectionObserver o) {
		observers.add(o);
	}

	@Override
	public void connected(CoronataWiiRemote wiiRemote) {
		for (CoronataConnectionObserver o : observers) {
			o.connected(wiiRemote);
		}
	}

}
