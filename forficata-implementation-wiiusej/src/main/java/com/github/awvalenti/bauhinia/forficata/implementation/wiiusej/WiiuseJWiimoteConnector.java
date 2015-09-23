package com.github.awvalenti.bauhinia.forficata.implementation.wiiusej;

import wiiusej.WiiUseApiManager;

import com.github.awvalenti.bauhinia.forficata.api.ForficataCallback;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnector;

public class WiiuseJWiimoteConnector implements WiimoteConnector {

	private final int maximumNumberOfWiimotes;

	public WiiuseJWiimoteConnector(int maximumNumberOfWiimotes) {
		this.maximumNumberOfWiimotes = maximumNumberOfWiimotes;
	}

	@Override
	public void run(final ForficataCallback callback) {
		wiiusej.Wiimote[] wiimotesFound = WiiUseApiManager.getWiimotes(maximumNumberOfWiimotes, false);
		for (wiiusej.Wiimote w : wiimotesFound) {
			callback.wiimoteConnected(new WiiuseJWiimoteAdapter(w));
		}
	}

}
