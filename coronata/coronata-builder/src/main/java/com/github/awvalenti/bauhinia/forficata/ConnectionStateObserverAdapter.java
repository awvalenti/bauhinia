package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.observers.CoronataConnectionStateObserver;
import com.github.awvalenti.bauhinia.forficata.observers.CoronataObserver;

class ConnectionStateObserverAdapter implements CoronataObserver {

	private final CoronataConnectionStateObserver output;
	private boolean connected = false;

	public ConnectionStateObserverAdapter(CoronataConnectionStateObserver output) {
		this.output = output;
		output.enteredIdleState();
	}

	@Override
	public void forficataStarted() {
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
	public void deviceIdentifiedAsNotWiimote(String address, String deviceClass) {
	}

	@Override
	public void wiimoteIdentified() {
	}

	@Override
	public void wiimoteConnected(Wiimote wiimote) {
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
