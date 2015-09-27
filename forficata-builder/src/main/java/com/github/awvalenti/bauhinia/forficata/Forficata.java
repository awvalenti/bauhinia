package com.github.awvalenti.bauhinia.forficata;

public class Forficata implements ForficataBuilderStep1, ForficataBuilderStep2, ForficataBuilderStep3, ForficataBuilderStep4 {

	private ForficataConfiguration config = new ForficataConfiguration();

	private Forficata() {
	}

	public static ForficataBuilderStep1 builder() {
		return new Forficata();
	}

	@Override
	public ForficataBuilderStep2 synchronousConnector() {
		config.synchronous = true;
		return this;
	}

	@Override
	public ForficataBuilderStep2 asynchronousConnector() {
		config.synchronous = false;
		return this;
	}

	@Override
	public ForficataBuilderStep3 oneWiimote(ForficataWiimoteListener listener) {
		config.wiimotesExpected = 1;
		config.wiimoteEventListener = listener;
		return this;
	}

	@Override
	public ForficataBuilderStep4 withPhaseListener(ForficataPhaseListener listener) {
		config.forficataPhaseListener = listener;
		return this;
	}

	@Override
	public ForficataBuilderStep4 withEventListener(ForficataEventListener listener) {
		config.forficataEventListener = listener;
		return this;
	}

	@Override
	public WiimoteConnector build() {
		return isWindows() ? new WiiuseJWiimoteConnector(config) : new BlueCoveWiimoteConnector(
				config);
	}

	private static boolean isWindows() {
		return System.getProperty("os.name").toLowerCase().contains("win");
	}

}
