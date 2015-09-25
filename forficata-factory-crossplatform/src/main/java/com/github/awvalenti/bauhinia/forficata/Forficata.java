package com.github.awvalenti.bauhinia.forficata.factory.crossplatform;

import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnector;
import com.github.awvalenti.bauhinia.forficata.implementation.bluecove.AsynchronousBlueCoveWiimoteConnector;
import com.github.awvalenti.bauhinia.forficata.implementation.bluecove.SynchronousBlueCoveWiimoteConnector;
import com.github.awvalenti.bauhinia.forficata.implementation.wiiusej.WiiuseJWiimoteConnector;

public abstract class Forficata {

	public static WiimoteConnector asyncConnector(int maxNumberOfWiimotes) {
		return isWindows() ? new WiiuseJWiimoteConnector(maxNumberOfWiimotes)
				: new AsynchronousBlueCoveWiimoteConnector(maxNumberOfWiimotes);
	}

	public static WiimoteConnector syncConnector(int maxNumberOfWiimotes) {
		return isWindows() ? new WiiuseJWiimoteConnector(maxNumberOfWiimotes)
				: new SynchronousBlueCoveWiimoteConnector(maxNumberOfWiimotes);
	}

	private static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

}
