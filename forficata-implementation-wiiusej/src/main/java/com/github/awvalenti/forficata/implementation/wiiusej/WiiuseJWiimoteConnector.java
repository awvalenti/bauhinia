package com.github.awvalenti.forficata.implementation.wiiusej;

import wiiusej.WiiUseApiManager;

import com.github.awvalenti.forficata.api.WiimoteConnectedCallback;
import com.github.awvalenti.forficata.api.WiimoteConnector;

public class WiiuseJWiimoteConnector implements WiimoteConnector {

	@Override
	public void searchAndConnect(final WiimoteConnectedCallback callback) {
		wiiusej.Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(1, false);
		if (wiimotes.length > 0) callback.wiimoteConnected(new WiiuseJWiimoteAdapter(wiimotes[0]));
	}

	@Override
	public void connectToWiimoteAt(String bluetoothAddress, WiimoteConnectedCallback callback) {
		// TODO
	}

}
