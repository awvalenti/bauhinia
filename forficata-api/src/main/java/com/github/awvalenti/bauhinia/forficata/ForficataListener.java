package com.github.awvalenti.bauhinia.forficata;

public interface ForficataListener {

	void librariesLoaded();

	void searchStarted();

	void bluetoothDeviceFound(String address, String deviceClass);

	void wiimoteIdentified();

	void wiimoteConnected(Wiimote wiimote);

	void searchFinished();

	void errorOccurred(ForficataException e);

}
