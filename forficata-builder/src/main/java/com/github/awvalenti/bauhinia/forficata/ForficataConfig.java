package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.listeners.ForficataWiimoteFullListener;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;

class ForficataConfig implements ReadableForficataConfig {

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
	public ForficataWiimoteFullListener getWiimoteListener() {
		return compositeListener;
	}

	public void addButtonListener(ForficataWiimoteFullListener l) {
		compositeListener.addListener(l);
	}

	public void addObserver(ForficataObserver o) {
		compositeObserver.add(o);
	}

	@Override
	public ForficataObserver getForficataEventListener() {
		return compositeObserver;
	}

}
