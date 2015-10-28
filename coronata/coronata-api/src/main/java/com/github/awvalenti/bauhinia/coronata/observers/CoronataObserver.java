package com.github.awvalenti.bauhinia.coronata.observers;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.Wiimote;

public interface CoronataObserver {

	void coronataStarted();

	void librariesLoaded();

	void searchStarted();

	void bluetoothDeviceFound(String address, String deviceClass);

	void deviceRejectedIdentification(String address, String deviceClass);

	void deviceIdentifiedAsNotWiimote(String address, String deviceClass);

	void wiimoteIdentified();

	void wiimoteConnected(Wiimote wiimote);

	void searchFinished();

	void errorOccurred(CoronataException e);

}
