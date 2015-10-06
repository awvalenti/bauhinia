package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.listeners.ForficataButtonListener;
import com.github.awvalenti.bauhinia.forficata.listeners.ForficataWiimoteFullListener;

public class ButtonListenerAdapter implements ForficataWiimoteFullListener {

	private final ForficataButtonListener output;

	public ButtonListenerAdapter(ForficataButtonListener output) {
		this.output = output;
	}

	@Override
	public void buttonPressed(WiimoteButton button) {
		output.buttonPressed(button);
	}

	@Override
	public void buttonReleased(WiimoteButton button) {
		output.buttonReleased(button);
	}

	@Override
	public void wiimoteDisconnected() {
	}

}
