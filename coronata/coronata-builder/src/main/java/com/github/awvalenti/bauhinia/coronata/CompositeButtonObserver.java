package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;

class CompositeButtonObserver implements CoronataButtonObserver {

	private final List<CoronataButtonObserver> observers = new ArrayList<CoronataButtonObserver>();

	public void add(CoronataButtonObserver o) {
		observers.add(o);
	}

	@Override
	public void buttonPressed(CoronataWiiRemoteButton button) {
		for (CoronataButtonObserver o : observers) {
			o.buttonPressed(button);
		}
	}

	@Override
	public void buttonReleased(CoronataWiiRemoteButton button) {
		for (CoronataButtonObserver o : observers) {
			o.buttonReleased(button);
		}
	}

}
