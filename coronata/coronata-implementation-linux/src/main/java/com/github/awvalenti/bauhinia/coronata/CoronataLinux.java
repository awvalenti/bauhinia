package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class CoronataLinux implements Coronata {

	private final BlueCoveExceptionFactory exceptionFactory = new BlueCoveExceptionFactory();

	private final ReadableCoronataConfig config;

	public CoronataLinux(ReadableCoronataConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		new Thread("Coronata") {
			@Override
			public void run() {
				CoronataLifecycleEventsObserver observer = config.getLifecycleEventsObserver();
				observer.coronataStarted();
		
				try {
					BlueCoveLibraryFacade blueCoveLib = new BlueCoveLibraryFacade();
					observer.libraryLoaded();
		
					Object monitor = new Object();
					blueCoveLib.startAsynchronousSearch(new WiiRemoteDiscoverer(exceptionFactory, config
							.getButtonObserver(), observer, monitor));
					observer.searchStarted();
		
					synchronized (monitor) {
						monitor.wait();
					}
		
				} catch (BluetoothStateException e) {
					observer.errorOccurred(exceptionFactory.correspondingTo(e));

				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		}.start();
	}

}
