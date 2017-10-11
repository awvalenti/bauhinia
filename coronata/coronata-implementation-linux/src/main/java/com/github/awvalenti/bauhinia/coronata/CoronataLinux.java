package com.github.awvalenti.bauhinia.coronata;

import java.util.concurrent.atomic.AtomicInteger;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.RemoteDevice;

import com.github.awvalenti.bauhinia.coronata.ConnectionAttemptTask.ConnectionResult;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class CoronataLinux implements Coronata {

	private static final AtomicInteger threadId = new AtomicInteger(0);

	private final WiiRemoteFactory wiiRemoteFactory = new WiiRemoteFactory();

	private final BlueCoveExceptionFactory exceptionFactory =
			new BlueCoveExceptionFactory();

	private final ReadableCoronataConfig config;

	public CoronataLinux(ReadableCoronataConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		new Thread("Coronata-" + threadId.getAndIncrement()) {
			@Override
			public void run() {
				runUntilConnectionOrTimeout();
			}
		}.start();
	}

	private void runUntilConnectionOrTimeout() {
		CoronataLifecycleEventsObserver leObserver =
				config.getLifecycleEventsObserver();

		try {
			leObserver.coronataStarted();

			BlueCoveLibraryFacade blueCoveLib = new BlueCoveLibraryFacade();
			leObserver.libraryLoaded();

			// TODO Another leObserver call?
			leObserver.searchStarted();

			doSearch(blueCoveLib, config.getMaximumBluetoothSearches(),
					config.getWiiRemotesExpected());

			leObserver.searchFinished();

		} catch (BluetoothStateException e) {
			leObserver.errorOccurred(exceptionFactory.correspondingTo(e));
		}
	}

	private void doSearch(BlueCoveLibraryFacade blueCoveLib, int maxSearches,
			int wiiRemotesExpected) throws BluetoothStateException {
		int connectedWiiRemotes = 0;

		ConnectionAttemptsQueue connectionAttempts =
				new ConnectionAttemptsQueue();

		for (int i = 0; i < maxSearches; ++i) {
			blueCoveLib.startSynchronousSearch(
					new DevicesGatherer(connectionAttempts));

			while (!connectionAttempts.isEmpty()) {
				ConnectionResult result = connectionAttempts.pop().run();
				if (result == ConnectionResult.SUCCESS &&
						++connectedWiiRemotes == wiiRemotesExpected) {
					return;
				}
			}
		}
	}

	class DevicesGatherer implements SimpleDiscoveryListener {
		private final ConnectionAttemptsQueue queue;

		public DevicesGatherer(ConnectionAttemptsQueue queue) {
			this.queue = queue;
		}

		@Override
		public void deviceDiscovered(RemoteDevice btDevice, DeviceClass clazz) {
			CoronataLifecycleEventsObserver leObserver =
					config.getLifecycleEventsObserver();

			leObserver.bluetoothDeviceFound(btDevice.getBluetoothAddress(),
					((Object) clazz).toString());

			queue.push(new ConnectionAttemptTask(btDevice, clazz, leObserver,
					config.getButtonObserver(), wiiRemoteFactory));
		}

	}

}
