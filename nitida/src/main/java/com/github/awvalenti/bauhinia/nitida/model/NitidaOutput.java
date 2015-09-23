package com.github.awvalenti.bauhinia.nitida.model;

public interface NitidaOutput {

	void enteredIdleState();

	void enteredSearchingStarted();

	void enteredActiveState();

	void bluetoothDeviceFound(String bluetoothAddress, String deviceClass);

	void unexpectedException(Exception e);

}
