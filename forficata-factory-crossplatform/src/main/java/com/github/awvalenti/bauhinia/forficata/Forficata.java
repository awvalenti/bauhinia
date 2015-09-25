package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.AsynchronousBlueCoveWiimoteConnector;
import com.github.awvalenti.bauhinia.forficata.SynchronousBlueCoveWiimoteConnector;
import com.github.awvalenti.bauhinia.forficata.WiimoteConnector;
import com.github.awvalenti.bauhinia.forficata.WiiuseJWiimoteConnector;

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
