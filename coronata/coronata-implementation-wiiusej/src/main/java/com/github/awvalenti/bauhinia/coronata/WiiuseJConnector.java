package com.github.awvalenti.bauhinia.coronata;

import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;

class WiiuseJConnector implements CoronataConnector {

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

		try {
			// This loads WiiuseJ classes and libraries
			WiiUseApiManager.getInstance();
			observer.librariesLoaded();
		} catch (ExceptionInInitializerError e) {
			// This happens if WiiuseJ fails to load native libraries for the first time.
			// Although catching this error is not a great thing to do, for current version of
			// WiiuseJ, it is the only alternative to find out that a problem occurred with native
			// libraries.

			// TODO Use exception factory
			observer.errorOccurred(new CoronataException(e, "Error loading native libraries"));

			return;
		} catch (NoClassDefFoundError e) {
			// This happens if WiiuseJ fails to load native libraries more than once.

			// TODO Use exception factory
			observer.errorOccurred(new CoronataException(e, "Error loading native libraries"));

			return;
		}

		observer.searchStarted();
		Wiimote[] wiimotesFound = WiiUseApiManager.getWiimotes(config.getWiiRemotesExpected(),
				false);
		if (wiimotesFound.length > 0) observer.wiiRemoteIdentified();
		for (Wiimote w : wiimotesFound) {
			observer.wiiRemoteConnected(new WiiuseJWiiRemoteAdapter(w, config.getWiiRemoteListener()));
		}
		observer.searchFinished();
	}

}
