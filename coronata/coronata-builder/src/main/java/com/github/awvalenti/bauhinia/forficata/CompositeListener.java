package com.github.awvalenti.bauhinia.forficata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.forficata.listeners.CoronataWiimoteFullListener;

class CompositeListener implements CoronataWiimoteFullListener {

	private final List<CoronataWiimoteFullListener> all = new ArrayList<CoronataWiimoteFullListener>();

	public void addListener(CoronataWiimoteFullListener l) {
		all.add(l);
	}

	@Override
	public void buttonPressed(WiimoteButton button) {
		for (CoronataWiimoteFullListener l : all) {
			l.buttonPressed(button);
		}
	}

	@Override
	public void buttonReleased(WiimoteButton button) {
		for (CoronataWiimoteFullListener l : all) {
			l.buttonReleased(button);
		}
	}

	@Override
	public void wiimoteDisconnected() {
		for (CoronataWiimoteFullListener l : all) {
			l.wiimoteDisconnected();
		}
	}

}
