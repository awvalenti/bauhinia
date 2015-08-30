package com.github.awvalenti.bauhinia.forficata.api;

public interface WiimoteListener {

	void wiimoteConnected();

	void buttonPressed(WiimoteButton button);

	void buttonReleased(WiimoteButton button);

	void wiimoteDisconnected();

}
