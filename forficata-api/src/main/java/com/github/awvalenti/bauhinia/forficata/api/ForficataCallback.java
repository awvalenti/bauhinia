package com.github.awvalenti.bauhinia.forficata.api;

public interface ForficataCallback {

	void bluetoothDeviceFound(String bluetoothAddress, String deviceClass);

	void wiimoteConnected(Wiimote wiimote);

	void errorOccurred(ForficataException e);

	void searchStarted();

}
