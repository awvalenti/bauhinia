package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;

import com.github.awvalenti.bauhinia.coronata.ReadableCoronataConfig;
import com.github.awvalenti.bauhinia.coronata.CoronataConnector;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;

class BlueCoveConnector implements CoronataConnector {

	private final ReadableCoronataConfig config;
	private BlueCoveLibraryFacade blueCoveLib;

	public BlueCoveConnector(ReadableCoronataConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		CoronataFullObserver observer = config.getCoronataObserver();
		observer.coronataStarted();

		try {
			blueCoveLib = new BlueCoveLibraryFacade();
			observer.librariesLoaded();

			Object monitor = new Object();
			blueCoveLib.startAsynchronousSearch(new BlueCoveListener(config.getWiiRemoteListener(),
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
