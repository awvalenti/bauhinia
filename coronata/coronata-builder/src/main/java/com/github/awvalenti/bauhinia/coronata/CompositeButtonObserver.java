package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;

class CompositeButtonObserver implements CoronataButtonObserver {

	private final List<CoronataButtonObserver> listeners = new ArrayList<CoronataButtonObserver>();

	public void add(CoronataButtonObserver l) {
		listeners.add(l);
	}

	@Override
	public void buttonPressed(CoronataWiiRemoteButton button) {
		for (CoronataButtonObserver l : listeners) {
			l.buttonPressed(button);
		}
	}

	@Override
	public void buttonReleased(CoronataWiiRemoteButton button) {
		for (CoronataButtonObserver l : listeners) {
			l.buttonReleased(button);
		}
	}

}
