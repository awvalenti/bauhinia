package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import com.github.awvalenti.bauhinia.forficata.api.ForficataCallback;

public class AsynchronousBlueCoveWiimoteConnector extends BlueCoveWiimoteConnector {

	private static final Runnable NOOP = new Runnable() {
		@Override
		public void run() {
		}
	};

	public AsynchronousBlueCoveWiimoteConnector(int maximumNumberOfWiimotes) {
		super(maximumNumberOfWiimotes);
	}

	@Override
	public void run(final ForficataCallback callback) {
		runAsyncSearch(callback, NOOP);
	}

}
