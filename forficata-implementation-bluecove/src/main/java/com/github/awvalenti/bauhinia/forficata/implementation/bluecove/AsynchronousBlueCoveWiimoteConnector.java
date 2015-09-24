package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import com.github.awvalenti.bauhinia.forficata.api.ForficataCallback;

public class AsynchronousBlueCoveWiimoteConnector extends BlueCoveWiimoteConnector {

	private static final Runnable NULL_OPERATION = new Runnable() {
		@Override
		public void run() {
		}
	};

	public AsynchronousBlueCoveWiimoteConnector(int maximumNumberOfWiimotes) {
		super(maximumNumberOfWiimotes);
	}

	@Override
	public void run(final ForficataCallback callback) {
		runAsyncSearch(callback, NULL_OPERATION);
	}

}
