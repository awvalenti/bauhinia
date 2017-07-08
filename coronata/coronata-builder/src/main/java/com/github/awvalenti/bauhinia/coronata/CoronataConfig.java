package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.ReadableCoronataConfig;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;

class CoronataConfig implements ReadableCoronataConfig {

	private Boolean synchronous;
	private Integer wiiRemotesExpected;
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
	public int getWiiRemotesExpected() {
		return wiiRemotesExpected;
	}

	public void setWiiRemotesExpected(int wiiRemotesExpected) {
		this.wiiRemotesExpected = wiiRemotesExpected;
	}

	@Override
	public WiiRemoteFullListener getWiiRemoteListener() {
		return compositeListener;
	}

	public void addButtonListener(WiiRemoteFullListener l) {
		compositeListener.addListener(l);
	}

	public void addObserver(CoronataFullObserver o) {
		compositeObserver.add(o);
	}

	@Override
	public CoronataFullObserver getCoronataObserver() {
		return compositeObserver;
	}

}
