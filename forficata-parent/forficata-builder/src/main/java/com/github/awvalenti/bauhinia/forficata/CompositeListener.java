package com.github.awvalenti.bauhinia.forficata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.forficata.listeners.ForficataWiimoteFullListener;

class CompositeListener implements ForficataWiimoteFullListener {

	private final List<ForficataWiimoteFullListener> all = new ArrayList<ForficataWiimoteFullListener>();

	public void addListener(ForficataWiimoteFullListener l) {
		all.add(l);
	}

	@Override
	public void buttonPressed(WiimoteButton button) {
		for (ForficataWiimoteFullListener l : all) {
			l.buttonPressed(button);
		}
	}

	@Override
	public void buttonReleased(WiimoteButton button) {
		for (ForficataWiimoteFullListener l : all) {
			l.buttonReleased(button);
		}
	}

	@Override
	public void wiimoteDisconnected() {
		for (ForficataWiimoteFullListener l : all) {
			l.wiimoteDisconnected();
		}
	}

}
