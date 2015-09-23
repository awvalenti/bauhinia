package com.github.awvalenti.bauhinia.nitida.model;

public interface NitidaOutput {

	void run();

	void idle();

	void searching();

	void bluetoothDeviceFound(String bluetoothAddress, String deviceClass);

	void identifying();

	void notWiimote();

	void active();

	void unexpectedException(Exception e);

}
