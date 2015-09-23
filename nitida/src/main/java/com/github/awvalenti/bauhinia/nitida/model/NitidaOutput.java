package com.github.awvalenti.bauhinia.nitida.model;

public interface NitidaOutput {

	void run();

	void enteredIdleState();

	void enteredSearchingStarted();

	void enteredActiveState();

	void bluetoothDeviceFound(String bluetoothAddress, String deviceClass);

	void unexpectedException(Exception e);

}
