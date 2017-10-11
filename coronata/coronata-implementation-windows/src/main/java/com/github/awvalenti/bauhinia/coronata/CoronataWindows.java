package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;
import com.github.awvalenti.wiiusej.WiiusejNativeLibraryLoadingException;

import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;

class CoronataWindows implements Coronata {

	private volatile static int threadId = 0;

	private final WiiuseJExceptionFactory exceptionFactory = new WiiuseJExceptionFactory();

	private final ReadableCoronataConfig config;

	public CoronataWindows(ReadableCoronataConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		new Thread("Coronata-" + threadId++) {
			@Override
			public void run() {
				CoronataLifecycleEventsObserver observer = config.getLifecycleEventsObserver();

				observer.coronataStarted();

				try {
					WiiUseApiManager wiiuseJ = new WiiUseApiManager();
					observer.libraryLoaded();

					observer.searchStarted();
					Wiimote[] wiimotesFound = wiiuseJ.getWiimotes(config.getWiiRemotesExpected());
					if (wiimotesFound.length > 0) {
						observer.identifiedAsWiiRemote(null);
					}
					for (Wiimote w : wiimotesFound) {
						observer.connected(
								new WiiuseJWiiRemote(w, config.getButtonObserver(), observer));
					}
					observer.searchFinished();

				} catch (WiiusejNativeLibraryLoadingException e) {
					observer.errorOccurred(exceptionFactory.errorLoadingNativeLibraries(e));
				}
			}
		}.start();
	}

}
