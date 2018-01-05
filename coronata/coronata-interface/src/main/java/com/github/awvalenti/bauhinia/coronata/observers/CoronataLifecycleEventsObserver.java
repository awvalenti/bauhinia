package com.github.awvalenti.bauhinia.coronata.observers;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemote;

public interface CoronataLifecycleEventsObserver extends
		CoronataDisconnectionObserver,
		CoronataErrorObserver {

	void coronataStarted();

	void searchStarted(boolean isWindows);

	void bluetoothDeviceFound(String btAddress, String deviceClass);

	void identificationRejected(String btAddress);

	void identifiedAsNonWiiRemote(String btAddress);

	void identifiedAsWiiRemote(String btAddressOrNull);

	void connectionRejected(String btAddress);

	void connected(CoronataWiiRemote wiiRemote);

	void searchFinished();

	@Override
	void disconnected();

	@Override
	void errorOccurred(CoronataException e);

}
