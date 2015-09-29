package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;

import wiiusej.WiiUseApiManager;

class WiiuseJWiimoteConnector implements WiimoteConnector {

	private final ReadableForficataConfig config;

	public WiiuseJWiimoteConnector(ReadableForficataConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				doSearch(config.getForficataEventListener());
			}
		};

		if (config.isSynchronous()) task.run();
		else new Thread(task).start();
	}

	private void doSearch(final ForficataObserver observer) {
		observer.forficataStarted();

		try {
			// This loads WiiuseJ classes and libraries
			WiiUseApiManager.getInstance();
			observer.librariesLoaded();
		} catch (ExceptionInInitializerError e) {
			// This happens when WiiuseJ fails to load native libraries.
			// Although catching this error is not a great thing to do, for current version of
			// WiiuseJ, it is the only alternative to find out that a problem occurred with native
			// libraries.

			// TODO Use exception factory
			observer.errorOccurred(new ForficataException(e, "Error loading native libraries"));

			return;
		}

		observer.searchStarted();
		wiiusej.Wiimote[] wiimotesFound = WiiUseApiManager.getWiimotes(
				config.getWiimotesExpected(), false);
		if (wiimotesFound.length > 0) observer.wiimoteIdentified();
		for (wiiusej.Wiimote w : wiimotesFound) {
			observer.wiimoteConnected(new WiiuseJWiimoteAdapter(w, config.getWiimoteListener()));
		}
		observer.searchFinished();
	}

}
