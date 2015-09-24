package com.github.awvalenti.bauhinia.forficata.implementation.wiiusej;

import wiiusej.WiiUseApiManager;

import com.github.awvalenti.bauhinia.forficata.api.ForficataListener;
import com.github.awvalenti.bauhinia.forficata.api.ForficataException;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnector;

public class WiiuseJWiimoteConnector implements WiimoteConnector {

	private final int maximumNumberOfWiimotes;

	public WiiuseJWiimoteConnector(int maximumNumberOfWiimotes) {
		this.maximumNumberOfWiimotes = maximumNumberOfWiimotes;
	}

	@Override
	public void run(final ForficataListener listener) {
		try {
			// This loads WiiuseJ classes and libraries
			WiiUseApiManager.getInstance();

			listener.searchStarted();
			wiiusej.Wiimote[] wiimotesFound = WiiUseApiManager.getWiimotes(maximumNumberOfWiimotes,
					false);
			for (wiiusej.Wiimote w : wiimotesFound) {
				listener.wiimoteConnected(new WiiuseJWiimoteAdapter(w));
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
