package com.github.awvalenti.bauhinia.forficata;

import static com.github.awvalenti.bauhinia.forficata.ConnectionState.*;

import com.github.awvalenti.bauhinia.forficata.observers.ForficataConnectionStateObserver;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;

class ConnectionStateObserverAdapter implements ForficataObserver {

	private final ForficataConnectionStateObserver output;
	private boolean connected = false;

	public ConnectionStateObserverAdapter(ForficataConnectionStateObserver output) {
		this.output = output;
		output.connectionStateChanged(IDLE);
	}

	@Override
	public void forficataStarted() {
		output.connectionStateChanged(CONNECTING);
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
	public void wiimoteIdentified() {
	}

	@Override
	public void wiimoteConnected(Wiimote wiimote) {
		connected = true;
	}

	@Override
	public void searchFinished() {
		if (connected) output.connectionStateChanged(CONNECTED);
	}

	@Override
	public void errorOccurred(ForficataException e) {
		output.connectionStateChanged(IDLE);
	}

}
