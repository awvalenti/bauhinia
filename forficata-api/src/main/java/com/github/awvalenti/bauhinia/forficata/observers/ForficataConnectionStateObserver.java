package com.github.awvalenti.bauhinia.forficata.observers;

public interface ForficataConnectionStateObserver {

	void enteredIdleState();

	void enteredInProcessState();

	void enteredConnectedState();

}
