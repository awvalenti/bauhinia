package com.github.awvalenti.bauhinia.coronata;

import java.util.concurrent.atomic.AtomicInteger;

class CoronataLinux implements Coronata, Runnable {

	private static final AtomicInteger threadId = new AtomicInteger(0);

	private final ReadableCoronataConfig config;

	private volatile LinuxConnectionStateMachine machine = null;

	public CoronataLinux(ReadableCoronataConfig config) {
		this.config = config;
	}

	@Override
	public void start() {
		synchronized (this) {
			if (machineIsRunning()) return;

			machine = new LinuxConnectionStateMachine(
					config.getLifecycleEventsObserver(),
					config.getTimeoutInSeconds(),
					config.getNumberOfWiiRemotes(), config.getButtonObserver());
		}

		new Thread(this, "Coronata-" + threadId.getAndIncrement()).start();
	}

	@Override
	public void stop() {
		if (machineIsRunning()) machine.requestStop();
	}

	private boolean machineIsRunning() {
		return machine != null;
	}

	@Override
	public void run() {
		machine.runSynchronously();
		machine = null;
	}

}
