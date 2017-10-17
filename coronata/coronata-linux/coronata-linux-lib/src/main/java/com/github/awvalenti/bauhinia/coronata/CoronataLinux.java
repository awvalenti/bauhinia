package com.github.awvalenti.bauhinia.coronata;

import java.util.concurrent.atomic.AtomicInteger;

class CoronataLinux implements Coronata, Runnable {

	private static final AtomicInteger threadId = new AtomicInteger(0);

	private final ReadableCoronataConfig config;

	private volatile ConnectionProcess process = null;

	public CoronataLinux(ReadableCoronataConfig config) {
		this.config = config;
	}

	@Override
	public void start() {
		synchronized (this) {
			if (processIsRunning()) return;

			process = new ConnectionProcess(config.getLifecycleEventsObserver(),
					config.getTimeoutInSeconds(),
					config.getNumberOfWiiRemotes(), config.getButtonObserver());
		}

		new Thread(this, "Coronata-" + threadId.getAndIncrement()).start();
	}

	@Override
	public void stop() {
		if (processIsRunning()) process.requestStop();
	}

	private boolean processIsRunning() {
		return process != null;
	}

	@Override
	public void run() {
		process.runSynchronously();
		process = null;
	}

}
