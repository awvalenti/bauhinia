package com.github.awvalenti.bauhinia.forficata;

class ForficataBuilder implements ForficataBuilderStep1, ForficataBuilderStep2,
		ForficataBuilderStep3 {

	private ForficataConfiguration config = new ForficataConfiguration();

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
		config.wiimoteListener = listener;
		return this;
	}

	@Override
	public ForficataBuilderStep3 eventListener(ForficataEventListener listener) {
		config.addForficataEventListener(listener);
		return this;
	}

	@Override
	public ForficataBuilderStep3 phaseListener(ForficataPhaseListener listener) {
		return eventListener(new EventListenerToPhaseListenerAdapter(listener));
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
