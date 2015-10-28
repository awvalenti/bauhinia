package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.listeners.CoronataButtonListener;
import com.github.awvalenti.bauhinia.forficata.listeners.CoronataWiimoteFullListener;

public class ButtonListenerAdapter implements CoronataWiimoteFullListener {

	private final CoronataButtonListener output;

	public ButtonListenerAdapter(CoronataButtonListener output) {
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
