package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.WiiRemote;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiiRemoteConnectionObserver;

class WiiRemoteConnectionObserverAdapter implements CoronataFullObserver {

	private final CoronataWiiRemoteConnectionObserver output;

	public WiiRemoteConnectionObserverAdapter(CoronataWiiRemoteConnectionObserver output) {
		this.output = output;
	}

	@Override
	public void coronataStarted() {
	}

	@Override
	public void librariesLoaded() {
	}

	@Override
	public void searchStarted() {
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
	}

	@Override
	public void wiiRemoteIdentified() {
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		output.wiiRemoteConnected(wiiRemote);
	}

	@Override
	public void searchFinished() {
	}

	@Override
	public void errorOccurred(CoronataException e) {
	}

}
