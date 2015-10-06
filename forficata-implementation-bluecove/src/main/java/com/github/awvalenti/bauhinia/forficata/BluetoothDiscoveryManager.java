package com.github.awvalenti.bauhinia.forficata;

class BluetoothDiscoveryManager {

	private volatile boolean inquiryCompleted = false;
	private volatile int devicesBeingHandled = 0;

	private final Runnable onFinish;

	public BluetoothDiscoveryManager(Runnable onFinish) {
		this.onFinish = onFinish;
	}

	public synchronized void beginDeviceDiscovered() {
		++devicesBeingHandled;
	}

	public synchronized void endDeviceDiscovered() {
		--devicesBeingHandled;
		checkFinish();
	}

	public synchronized void inquiryCompleted() {
		inquiryCompleted = true;
		checkFinish();
	}

	private void checkFinish() {
		if (inquiryCompleted && devicesBeingHandled == 0) onFinish.run();
	}

}
