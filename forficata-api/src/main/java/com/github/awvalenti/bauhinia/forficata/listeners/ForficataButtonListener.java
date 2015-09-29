package com.github.awvalenti.bauhinia.forficata.listeners;

import com.github.awvalenti.bauhinia.forficata.WiimoteButton;

public interface ForficataButtonListener {

	void buttonPressed(WiimoteButton button);

	void buttonReleased(WiimoteButton button);

}
