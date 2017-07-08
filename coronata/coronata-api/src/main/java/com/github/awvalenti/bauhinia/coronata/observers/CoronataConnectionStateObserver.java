package com.github.awvalenti.bauhinia.coronata.observers;

public interface CoronataConnectionStateObserver {

	void enteredIdleState();

	void enteredInProcessState();

	void enteredConnectedState();

}
