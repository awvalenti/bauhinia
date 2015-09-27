package com.github.awvalenti.bauhinia.forficata;

import wiiusej.WiiUseApiManager;

class WiiuseJWiimoteConnector implements WiimoteConnector {

	private final ForficataConfiguration config;

	public WiiuseJWiimoteConnector(ForficataConfiguration config) {
		this.config = config;
	}

	@Override
	public void start() {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				doSearch(config.getForficataEventListener());
			}
		};

		if (config.synchronous) task.run();
		else new Thread(task).start();
	}

	private void doSearch(final ForficataEventListener listener) {
		try {
			// This loads WiiuseJ classes and libraries
			WiiUseApiManager.getInstance();
			listener.librariesLoaded();

			listener.searchStarted();
			wiiusej.Wiimote[] wiimotesFound = WiiUseApiManager.getWiimotes(config.wiimotesExpected,
					false);
			if (wiimotesFound.length == config.wiimotesExpected) listener.wiimoteIdentified();
			for (wiiusej.Wiimote w : wiimotesFound) {
				listener.wiimoteConnected(new WiiuseJWiimoteAdapter(w, config.wiimoteListener));
			}
			listener.searchFinished();

		} catch (ExceptionInInitializerError e) {
			// This happens when WiiuseJ fails to load native libraries.
			// Although catching this error is not a great thing to do, for current version of
			// WiiuseJ, it is the only alternative to find out that a problem occurred with native
			// libraries.

			// TODO Use exception factory
			listener.errorOccurred(new ForficataException(e, "Error loading native libraries"));
		}
	}
}
