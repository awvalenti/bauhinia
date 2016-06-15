package com.github.awvalenti.bauhinia.coronata;

import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;
import com.github.awvalenti.wiiusej.WiiusejNativeLibraryLoadingException;

class WiiuseJConnector implements CoronataConnector {

	private final CoronataWiiusejExceptionFactory exceptionFactory = new CoronataWiiusejExceptionFactory();

	private final ReadableCoronataConfig config;

	public WiiuseJConnector(ReadableCoronataConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				doSearch(config.getCoronataObserver());
			}
		};

		if (config.isSynchronous()) task.run();
		else new Thread(task).start();
	}

	private void doSearch(final CoronataFullObserver observer) {
		observer.coronataStarted();

		final WiiUseApiManager wiiUseApiManager;

		try {
			wiiUseApiManager = new WiiUseApiManager();
			observer.libraryLoaded();

			observer.searchStarted();
			Wiimote[] wiimotesFound = wiiUseApiManager.getWiimotes(config.getWiiRemotesExpected(),
					false);
			if (wiimotesFound.length > 0) observer.wiiRemoteIdentified();
			for (Wiimote w : wiimotesFound) {
				observer.wiiRemoteConnected(new WiiuseJWiiRemoteAdapter(w, config.getWiiRemoteListener()));
			}
			observer.searchFinished();

		} catch (WiiusejNativeLibraryLoadingException e) {
			observer.errorOccurred(exceptionFactory.errorLoadingNativeLibraries(e));
		}
	}

}
