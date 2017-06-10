package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiiRemoteConnectionObserver;

class CompositeConnectionObserver implements CoronataWiiRemoteConnectionObserver {

	private final List<CoronataWiiRemoteConnectionObserver> observers = new ArrayList<CoronataWiiRemoteConnectionObserver>();

	public void add(CoronataWiiRemoteConnectionObserver o) {
		observers.add(o);
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		for (CoronataWiiRemoteConnectionObserver o : observers) {
			o.wiiRemoteConnected(wiiRemote);
		}
	}

	@Override
	public void wiiRemoteDisconnected(WiiRemote wiiRemote) {
		for (CoronataWiiRemoteConnectionObserver o : observers) {
			o.wiiRemoteDisconnected(wiiRemote);
		}
	}

}
