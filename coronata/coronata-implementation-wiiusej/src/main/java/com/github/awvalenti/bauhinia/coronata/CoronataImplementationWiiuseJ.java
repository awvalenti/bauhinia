package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;
import com.github.awvalenti.wiiusej.WiiusejNativeLibraryLoadingException;

import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;

class CoronataImplementationWiiuseJ implements Coronata {

	private final WiiuseJExceptionFactory exceptionFactory = new WiiuseJExceptionFactory();

	private final ReadableCoronataConfig config;

	public CoronataImplementationWiiuseJ(ReadableCoronataConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				doSearch(config.getLifecycleEventsObserver());
			}
		};

		if (config.isSynchronous()) task.run();
		else new Thread(task).start();
	}

	private void doSearch(final CoronataLifecycleEventsObserver observer) {
		observer.coronataStarted();

		final WiiUseApiManager wiiUseApiManager;

		try {
			wiiUseApiManager = new WiiUseApiManager();
			observer.libraryLoaded();

			observer.searchStarted();
			Wiimote[] wiimotesFound = wiiUseApiManager.getWiimotes(config.getWiiRemotesExpected());
			if (wiimotesFound.length > 0) observer.wiiRemoteIdentified();
			for (Wiimote w : wiimotesFound) {
				observer.connected(
						new WiiuseJWiiRemoteAdapter(w, config.getButtonObserver(), config.getLifecycleEventsObserver()));
			}
			observer.searchFinished();

		} catch (WiiusejNativeLibraryLoadingException e) {
			observer.errorOccurred(exceptionFactory.errorLoadingNativeLibraries(e));
		}
	}

}
