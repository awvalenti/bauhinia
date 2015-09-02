package com.github.awvalenti.forficata.factory.crossplatform;

import com.github.awvalenti.forficata.api.ForficataFactory;
import com.github.awvalenti.forficata.api.WiimoteConnector;
import com.github.awvalenti.forficata.implementation.bluecove.BlueCoveWiimoteConnector;
import com.github.awvalenti.forficata.implementation.wiiusej.WiiuseJWiimoteConnector;

public class ForficataFactoryCrossplatform implements ForficataFactory {

	@Override
	public WiimoteConnector createConnector() {
		// TODO improve platform discovery mechanism
		return isWindows() ? new WiiuseJWiimoteConnector() : new BlueCoveWiimoteConnector();
	}

	private boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

}
