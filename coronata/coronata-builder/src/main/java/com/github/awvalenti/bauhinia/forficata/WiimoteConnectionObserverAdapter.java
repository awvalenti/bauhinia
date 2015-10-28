package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.observers.CoronataObserver;
import com.github.awvalenti.bauhinia.forficata.observers.CoronataWiimoteConnectionObserver;

class WiimoteConnectionObserverAdapter implements CoronataObserver {

	private final CoronataWiimoteConnectionObserver output;

	public WiimoteConnectionObserverAdapter(CoronataWiimoteConnectionObserver output) {
		this.output = output;
	}

	@Override
	public void forficataStarted() {
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
		output.wiimoteConnected(wiimote);
	}

	@Override
	public void searchFinished() {
	}

	@Override
	public void errorOccurred(CoronataException e) {
	}

}
