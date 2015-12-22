package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.WiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;

class CompositeListener implements WiiRemoteFullListener {

	private final List<WiiRemoteFullListener> all = new ArrayList<WiiRemoteFullListener>();

	public void addListener(WiiRemoteFullListener l) {
		all.add(l);
	}

	@Override
	public void buttonPressed(WiiRemoteButton button) {
		for (WiiRemoteFullListener l : all) {
			l.buttonPressed(button);
		}
	}

	@Override
	public void buttonReleased(WiiRemoteButton button) {
		for (WiiRemoteFullListener l : all) {
			l.buttonReleased(button);
		}
	}

	@Override
	public void wiiRemoteDisconnected() {
		for (WiiRemoteFullListener l : all) {
			l.wiiRemoteDisconnected();
		}
	}

}
