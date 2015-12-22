package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.WiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteButtonListener;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;

public class ButtonListenerAdapter implements WiiRemoteFullListener {

	private final WiiRemoteButtonListener output;

	public ButtonListenerAdapter(WiiRemoteButtonListener output) {
		this.output = output;
	}

	@Override
	public void buttonPressed(WiiRemoteButton button) {
		output.buttonPressed(button);
	}

	@Override
	public void buttonReleased(WiiRemoteButton button) {
		output.buttonReleased(button);
	}

	@Override
	public void wiiRemoteDisconnected() {
	}

}
