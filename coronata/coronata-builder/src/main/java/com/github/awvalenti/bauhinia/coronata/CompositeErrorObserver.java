package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataErrorObserver;

class CompositeErrorObserver implements CoronataErrorObserver {

	private final List<CoronataErrorObserver> observers =
			new ArrayList<CoronataErrorObserver>();

	public void add(CoronataErrorObserver o) {
		observers.add(o);
	}

	@Override
	public void errorOccurred(CoronataException e) {
		for (CoronataErrorObserver o : observers) {
			o.errorOccurred(e);
		}
	}

}
