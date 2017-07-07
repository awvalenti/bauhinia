package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class CoronataImplementationBlueCove implements Coronata {

	private BlueCoveLibraryFacade blueCoveLib;

	private final BlueCoveExceptionFactory exceptionFactory = new BlueCoveExceptionFactory();

	private final ReadableCoronataConfig config;

	public CoronataImplementationBlueCove(ReadableCoronataConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		CoronataLifecycleEventsObserver observer = config.getLifecycleEventsObserver();
		observer.coronataStarted();

		try {
			blueCoveLib = new BlueCoveLibraryFacade();
			observer.libraryLoaded();

			Object monitor = new Object();
			blueCoveLib.startAsynchronousSearch(new BlueCoveListener(exceptionFactory, config
					.getButtonObserver(), observer, monitor));
			observer.searchStarted();

			if (config.isSynchronous()) {
				synchronized (monitor) {
					monitor.wait();
				}
			}

		} catch (BluetoothStateException e) {
			observer.errorOccurred(exceptionFactory.correspondingTo(e));

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
