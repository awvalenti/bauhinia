package com.github.awvalenti.bauhinia.coronata.observers;

import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemoteButton;

public interface CoronataButtonObserver {

	void buttonPressed(CoronataWiiRemoteButton button);

	void buttonReleased(CoronataWiiRemoteButton button);

}
