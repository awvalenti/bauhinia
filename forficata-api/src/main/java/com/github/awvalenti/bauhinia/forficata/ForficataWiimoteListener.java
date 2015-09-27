package com.github.awvalenti.bauhinia.forficata;

public interface ForficataWiimoteListener {

	void buttonPressed(WiimoteButton button);

	void buttonReleased(WiimoteButton button);

	void wiimoteDisconnected();

}
