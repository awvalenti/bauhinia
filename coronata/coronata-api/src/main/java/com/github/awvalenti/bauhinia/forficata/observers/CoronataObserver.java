package com.github.awvalenti.bauhinia.forficata.observers;

import com.github.awvalenti.bauhinia.forficata.CoronataException;
import com.github.awvalenti.bauhinia.forficata.Wiimote;

public interface CoronataObserver {

	void forficataStarted();

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
