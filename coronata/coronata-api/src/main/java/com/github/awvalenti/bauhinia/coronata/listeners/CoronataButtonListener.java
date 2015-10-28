package com.github.awvalenti.bauhinia.coronata.listeners;

import com.github.awvalenti.bauhinia.coronata.WiimoteButton;

public interface CoronataButtonListener {

	void buttonPressed(WiimoteButton button);

	void buttonReleased(WiimoteButton button);

}
