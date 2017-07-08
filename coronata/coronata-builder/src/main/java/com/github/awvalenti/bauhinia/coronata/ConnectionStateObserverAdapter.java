package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.WiiRemote;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;

class ConnectionStateObserverAdapter implements CoronataFullObserver {

	private final CoronataConnectionStateObserver output;
	private boolean connected = false;

	public ConnectionStateObserverAdapter(CoronataConnectionStateObserver output) {
		this.output = output;
		output.enteredIdleState();
	}

	@Override
	public void coronataStarted() {
		output.enteredInProcessState();
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
		connected = true;
		output.enteredConnectedState();
	}

	@Override
	public void searchFinished() {
		if (!connected) output.enteredIdleState();
	}

	@Override
	public void errorOccurred(CoronataException e) {
		output.enteredIdleState();
	}

}
