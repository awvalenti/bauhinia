package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteDisconnectionListener;

class CompositeDisconnectionListener implements WiiRemoteDisconnectionListener {

	private final List<WiiRemoteDisconnectionListener> listeners = new ArrayList<WiiRemoteDisconnectionListener>();

	public void add(WiiRemoteDisconnectionListener l) {
		listeners.add(l);
	}

	@Override
	public void wiiRemoteDisconnected() {
		for (WiiRemoteDisconnectionListener l : listeners) {
			l.wiiRemoteDisconnected();
		}
	}

}
