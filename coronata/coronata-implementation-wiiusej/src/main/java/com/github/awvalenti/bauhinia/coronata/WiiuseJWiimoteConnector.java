package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.ReadableCoronataConfig;
import com.github.awvalenti.bauhinia.coronata.WiimoteConnector;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataObserver;

import wiiusej.WiiUseApiManager;

class WiiuseJWiimoteConnector implements WiimoteConnector {

	private final ReadableCoronataConfig config;

	public WiiuseJWiimoteConnector(ReadableCoronataConfig config) {
		this.config = config;
	}

	@Override
	public void run() {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				doSearch(config.getForficataObserver());
			}
		};

		if (config.isSynchronous()) task.run();
		else new Thread(task).start();
	}

	private void doSearch(final CoronataObserver observer) {
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
			observer.errorOccurred(new CoronataException(e, "Error loading native libraries"));

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
