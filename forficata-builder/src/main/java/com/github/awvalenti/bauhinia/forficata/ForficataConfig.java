package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;

class ForficataConfig implements ReadableForficataConfig {

	private Boolean synchronous;
	private Integer wiimotesExpected;
	private ForficataWiimoteListener wiimoteListener;
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
	public ForficataWiimoteListener getWiimoteListener() {
		return wiimoteListener;
	}

	public void setWiimoteListener(ForficataWiimoteListener l) {
		this.wiimoteListener = l;
	}

	public void addObserver(ForficataObserver o) {
		compositeObserver.add(o);
	}

	@Override
	public ForficataObserver getForficataEventListener() {
		return compositeObserver;
	}

}
