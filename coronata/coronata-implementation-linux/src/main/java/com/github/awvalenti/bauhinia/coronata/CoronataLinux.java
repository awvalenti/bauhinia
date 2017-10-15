package com.github.awvalenti.bauhinia.coronata;

import java.util.concurrent.atomic.AtomicInteger;

class CoronataLinux implements Coronata {

	private static final AtomicInteger threadId = new AtomicInteger(0);

	private final ReadableCoronataConfig config;
	private final WiiRemoteFactory wiiRemoteFactory;

	private ConnectionProcess process;

	public CoronataLinux(ReadableCoronataConfig config) {
		this.config = config;

		wiiRemoteFactory = new WiiRemoteFactory(config.getButtonObserver(),
				config.getLifecycleEventsObserver());
	}

	@Override
	public void run() {
		new Thread("Coronata-" + threadId.getAndIncrement()) {
			@Override
			public void run() {
				runConnectionProcess();
			}
		}.start();
	}

	private void runConnectionProcess() {
		if (processIsRunning()) return;

		process = new ConnectionProcess(config.getMinimumTimeoutInSeconds(),
				config.getWiiRemotesExpected(),
				config.getLifecycleEventsObserver(), wiiRemoteFactory);

		process.run();

		process = null;
	}

	@Override
	public void requestStop() {
		if (processIsRunning()) process.requestStop();
	}

	private boolean processIsRunning() {
		return process != null;
	}

}
