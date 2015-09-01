package com.github.awvalenti.forficata.implementation.wiimotej;

import wiiusej.WiiUseApiManager;

import com.github.awvalenti.forficata.api.WiimoteConnectedCallback;
import com.github.awvalenti.forficata.api.WiimoteConnector;

public class WiimoteJWiimoteConnector implements WiimoteConnector {

	@Override
	public void searchAndConnect(final WiimoteConnectedCallback callback) {
		wiiusej.Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(1, false);
		if (wiimotes.length > 0) callback.wiimoteConnected(new WiimoteJWiimoteAdapter(wiimotes[0]));
	}

	@Override
	public void connectToWiimoteAt(String bluetoothAddress, WiimoteConnectedCallback callback) {
		// TODO
	}

}
