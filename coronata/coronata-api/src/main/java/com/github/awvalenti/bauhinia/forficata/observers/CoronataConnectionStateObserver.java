package com.github.awvalenti.bauhinia.forficata.observers;

public interface CoronataConnectionStateObserver {

	void enteredIdleState();

	void enteredInProcessState();

	void enteredConnectedState();

}
