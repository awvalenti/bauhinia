package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataWiimoteConnectionObserver;

class WiimoteConnectionObserverAdapter implements ForficataObserver {

	private final ForficataWiimoteConnectionObserver output;

	public WiimoteConnectionObserverAdapter(ForficataWiimoteConnectionObserver output) {
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
	public void errorOccurred(ForficataException e) {
	}

}
