package com.github.awvalenti.bauhinia.coronata;

import java.util.concurrent.atomic.AtomicInteger;

class CoronataLinux implements Coronata, Runnable {

	private static final AtomicInteger threadId = new AtomicInteger(0);

	private final ReadableCoronataConfig config;

	private LinuxConnectionStateMachine machine;

	public CoronataLinux(ReadableCoronataConfig config) {
		this.config = config;
	}

	@Override
	public synchronized void startConnectionProcess() {
		if (machine != null) return;

		machine = new LinuxConnectionStateMachine(
				config.getLifecycleEventsObserver(),
				config.getTimeoutInSeconds(),
				config.getNumberOfWiiRemotes(), config.getButtonObserver());

		new Thread(this, "Coronata-" + threadId.getAndIncrement()).start();
	}

	@Override
	public synchronized void stopConnectionProcessIfActive() {
		if (machine == null) return;

		machine.requestStop();
	}

	@Override
	public void run() {
		machine.run();
		synchronized (this) {
			machine = null;
		}
	}

}
