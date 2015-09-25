package com.github.awvalenti.bauhinia.forficata;

public abstract class ForficataFactory {

	public static WiimoteConnector asyncConnector(int maxNumberOfWiimotes) {
		return isWindows() ? new WiiuseJWiimoteConnector(maxNumberOfWiimotes)
				: new BlueCoveWiimoteConnector(maxNumberOfWiimotes, false);
	}

	public static WiimoteConnector syncConnector(int maxNumberOfWiimotes) {
		return isWindows() ? new WiiuseJWiimoteConnector(maxNumberOfWiimotes)
				: new BlueCoveWiimoteConnector(maxNumberOfWiimotes, true);
	}

	private static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

}
