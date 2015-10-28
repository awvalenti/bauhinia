package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.Wiimote;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiimoteConnectionObserver;

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
