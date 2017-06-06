package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.ReadableCoronataConfig;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteButtonListener;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiiRemoteConnectionObserver;

// TODO Merge with CoronataBuilder class
class CoronataConfig implements ReadableCoronataConfig {

	private Boolean synchronous;
	private Integer wiiRemotesExpected;
	
	private final Mediator mediator = new Mediator();

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
		return mediator;
	}

	@Override
	public CoronataFullObserver getCoronataObserver() {
		return mediator;
	}

	public void addButtonListener(WiiRemoteButtonListener l) {
		mediator.addButtonListener(l);
	}

	public void addConnectionObserver(CoronataWiiRemoteConnectionObserver o) {
		mediator.addConnectionObserver(o);
	}

	public void addFullObserver(CoronataFullObserver o) {
		mediator.addFullObserver(o);
	}

	public void addPhaseStateObserver(CoronataPhaseObserver o) {
		mediator.addPhaseStateObserver(o);		
	}

	public void addConnectionStateObserver(CoronataConnectionStateObserver o) {
		mediator.addConnectionStateObserver(o);
	}

}
