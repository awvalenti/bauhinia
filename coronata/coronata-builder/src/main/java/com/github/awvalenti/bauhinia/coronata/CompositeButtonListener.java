package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteButtonListener;

class CompositeButtonListener implements WiiRemoteButtonListener {

	private final List<WiiRemoteButtonListener> listeners = new ArrayList<WiiRemoteButtonListener>();

	public void add(WiiRemoteButtonListener l) {
		listeners.add(l);
	}

	@Override
	public void buttonPressed(WiiRemoteButton button) {
		for (WiiRemoteButtonListener l : listeners) {
			l.buttonPressed(button);
		}
	}

	@Override
	public void buttonReleased(WiiRemoteButton button) {
		for (WiiRemoteButtonListener l : listeners) {
			l.buttonReleased(button);
		}
	}

}
