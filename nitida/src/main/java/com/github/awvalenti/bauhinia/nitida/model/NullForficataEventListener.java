package com.github.awvalenti.bauhinia.nitida.model;

import com.github.awvalenti.bauhinia.forficata.ForficataEventListener;
import com.github.awvalenti.bauhinia.forficata.ForficataException;
import com.github.awvalenti.bauhinia.forficata.Wiimote;

public class NullForficataEventListener implements ForficataEventListener {

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
	}

	@Override
	public void searchFinished() {
	}

	@Override
	public void errorOccurred(ForficataException e) {
	}

}
