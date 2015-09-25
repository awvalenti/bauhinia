package com.github.awvalenti.bauhinia.forficata;

public interface ForficataListener {

	void searchStarted();

	void identifyingBluetoothDevice(String bluetoothAddress, String deviceClass);

	void wiimoteFound();

	void wiimoteConnected(Wiimote wiimote);

	void searchFinished();

	void errorOccurred(ForficataException e);

}
