package com.github.awvalenti.bauhinia.forficata.factory.crossplatform;

import com.github.awvalenti.bauhinia.forficata.api.ForficataFactory;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnector;
import com.github.awvalenti.bauhinia.forficata.implementation.bluecove.BlueCoveWiimoteConnector;
import com.github.awvalenti.bauhinia.forficata.implementation.wiiusej.WiiuseJWiimoteConnector;

public class ForficataFactoryCrossplatform implements ForficataFactory {

	@Override
	public WiimoteConnector createConnector(int maxNumberOfWiimotes) {
		return isWindows() ? new WiiuseJWiimoteConnector(maxNumberOfWiimotes)
				: new BlueCoveWiimoteConnector(maxNumberOfWiimotes);
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

}
