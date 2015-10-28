package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;

import com.github.awvalenti.bauhinia.coronata.ReadableCoronataConfig;
import com.github.awvalenti.bauhinia.coronata.WiimoteConnector;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataObserver;

class BlueCoveWiimoteConnector implements WiimoteConnector {

	private final ReadableCoronataConfig config;
	private BlueCoveLibraryFacade blueCoveLib;

	public BlueCoveWiimoteConnector(ReadableCoronataConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		CoronataObserver observer = config.getForficataObserver();
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
			observer.errorOccurred(CoronataExceptionFactory.correspondingTo(e));

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
