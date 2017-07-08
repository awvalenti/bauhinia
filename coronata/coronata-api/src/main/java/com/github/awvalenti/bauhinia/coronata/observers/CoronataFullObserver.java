package com.github.awvalenti.bauhinia.coronata.observers;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.WiiRemote;

public interface CoronataFullObserver {

	void coronataStarted();

	void librariesLoaded();

	void searchStarted();

	void bluetoothDeviceFound(String address, String deviceClass);

	void deviceRejectedIdentification(String address, String deviceClass);

	void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass);

	void wiiRemoteIdentified();

	void wiiRemoteConnected(WiiRemote wiiRemote);

	void searchFinished();

	void errorOccurred(CoronataException e);

}
