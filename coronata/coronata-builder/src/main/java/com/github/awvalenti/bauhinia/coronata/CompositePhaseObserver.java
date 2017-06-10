package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;

class CompositePhaseObserver implements CoronataPhaseObserver {

	private final List<CoronataPhaseObserver> observers = new ArrayList<CoronataPhaseObserver>();

	public void add(CoronataPhaseObserver o) {
		observers.add(o);
	}

	@Override
	public void starting() {
		for (CoronataPhaseObserver o : observers) {
			o.starting();
		}
	}

	@Override
	public void running(CoronataPhase coronataPhase) {
		for (CoronataPhaseObserver o : observers) {
			o.running(coronataPhase);
		}
	}

	@Override
	public void success(CoronataPhase coronataPhase) {
		for (CoronataPhaseObserver o : observers) {
			o.success(coronataPhase);
		}
	}

	@Override
	public void failure(CoronataPhase coronataPhase) {
		for (CoronataPhaseObserver o : observers) {
			o.failure(coronataPhase);
		}
	}

}
