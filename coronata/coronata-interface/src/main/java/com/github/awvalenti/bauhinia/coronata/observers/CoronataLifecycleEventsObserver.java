package com.github.awvalenti.bauhinia.coronata.observers;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemote;

public interface CoronataLifecycleEventsObserver extends
		CoronataDisconnectionObserver, CoronataErrorObserver {

	void coronataStarted();

	void libraryLoaded();

	void searchStarted();

	void bluetoothDeviceFound(String address, String deviceClass);

	void deviceRejectedIdentification(String address, String deviceClass);

	void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass);

	void wiiRemoteIdentified();

	void connected(CoronataWiiRemote wiiRemote);

	void searchFinished();

	@Override
	void disconnected();

	@Override
	void errorOccurred(CoronataException e);

}
