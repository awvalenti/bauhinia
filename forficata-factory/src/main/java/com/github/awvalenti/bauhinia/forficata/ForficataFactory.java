package com.github.awvalenti.bauhinia.forficata;

public abstract class ForficataFactory {

	public static WiimoteConnector asyncConnector(int maxNumberOfWiimotes) {
		return createConnector(maxNumberOfWiimotes, false);
	}

	public static WiimoteConnector syncConnector(int maxNumberOfWiimotes) {
		return createConnector(maxNumberOfWiimotes, true);
	}

	private static WiimoteConnector createConnector(int maxNumberOfWiimotes, boolean synchronous) {
		return isWindows() ? new WiiuseJWiimoteConnector(maxNumberOfWiimotes, synchronous)
				: new BlueCoveWiimoteConnector(maxNumberOfWiimotes, synchronous);
	}

	private static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

}
