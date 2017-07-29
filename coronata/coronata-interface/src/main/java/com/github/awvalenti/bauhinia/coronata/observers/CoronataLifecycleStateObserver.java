package com.github.awvalenti.bauhinia.coronata.observers;

public interface CoronataLifecycleStateObserver {

	void enteredIdleState();

	void enteredInProcessState();

	void enteredConnectedState();

}
