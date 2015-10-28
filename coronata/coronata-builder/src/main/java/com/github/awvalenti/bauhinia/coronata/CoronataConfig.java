package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.ReadableCoronataConfig;
import com.github.awvalenti.bauhinia.coronata.listeners.CoronataWiimoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataObserver;

class CoronataConfig implements ReadableCoronataConfig {

	private Boolean synchronous;
	private Integer wiimotesExpected;
	private CompositeListener compositeListener = new CompositeListener();
	private CompositeObserver compositeObserver = new CompositeObserver();

	@Override
	public boolean isSynchronous() {
		return synchronous;
	}

	public void setSynchronous(boolean synchronous) {
		this.synchronous = synchronous;
	}

	@Override
	public int getWiimotesExpected() {
		return wiimotesExpected;
	}

	public void setWiimotesExpected(int wiimotesExpected) {
		this.wiimotesExpected = wiimotesExpected;
	}

	@Override
	public CoronataWiimoteFullListener getWiimoteListener() {
		return compositeListener;
	}

	public void addButtonListener(CoronataWiimoteFullListener l) {
		compositeListener.addListener(l);
	}

	public void addObserver(CoronataObserver o) {
		compositeObserver.add(o);
	}

	@Override
	public CoronataObserver getForficataObserver() {
		return compositeObserver;
	}

}
