package com.github.awvalenti.bauhinia.forficata.api;

public interface WiimoteButtonListener {

	void buttonPressed(WiimoteButton button);

	void buttonReleased(WiimoteButton button);

	void wiimoteDisconnected();

}
