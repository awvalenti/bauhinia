package com.github.awvalenti.bauhinia.coronata.listeners;

import com.github.awvalenti.bauhinia.coronata.WiiRemoteButton;

public interface WiiRemoteButtonListener {

	void buttonPressed(WiiRemoteButton button);

	void buttonReleased(WiiRemoteButton button);

}
