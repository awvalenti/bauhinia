package com.github.awvalenti.bauhinia.forficata;

import javax.bluetooth.BluetoothStateException;

import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;

class BlueCoveWiimoteConnector implements WiimoteConnector {

	private final ReadableForficataConfig config;
	private BlueCoveLibraryFacade blueCoveLib;

	public BlueCoveWiimoteConnector(ReadableForficataConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		ForficataObserver observer = config.getForficataObserver();
		observer.forficataStarted();

		try {
			blueCoveLib = new BlueCoveLibraryFacade();
			observer.librariesLoaded();

			Object monitor = new Object();
			blueCoveLib.startAsynchronousSearch(new BlueCoveListener(config.getWiimoteListener(),
					observer, monitor));
			observer.searchStarted();

			if (config.isSynchronous()) {
				synchronized (monitor) {
					monitor.wait();
				}
			}

		} catch (BluetoothStateException e) {
			observer.errorOccurred(ForficataExceptionFactory.correspondingTo(e));

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
