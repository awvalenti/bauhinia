package com.github.awvalenti.forficata.api;

public interface WiimoteListener {

	void buttonPressed(WiimoteButton button);

	void buttonReleased(WiimoteButton button);

	void wiimoteDisconnected();

}
