package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;

import com.github.awvalenti.bauhinia.coronata.ReadableCoronataConfig;
import com.github.awvalenti.bauhinia.coronata.CoronataConnector;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;

class BlueCoveConnector implements CoronataConnector {

	private BlueCoveLibraryFacade blueCoveLib;

	private final CoronataExceptionFactory exceptionFactory = new CoronataExceptionFactory();

	private final ReadableCoronataConfig config;

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
			blueCoveLib.startAsynchronousSearch(new BlueCoveListener(exceptionFactory, config
					.getWiiRemoteListener(), observer, monitor));
			observer.searchStarted();

			if (config.isSynchronous()) {
				synchronized (monitor) {
					monitor.wait();
				}
			}

		} catch (BluetoothStateException e) {
			observer.errorOccurred(exceptionFactory.forBlueCoveException(e));

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
