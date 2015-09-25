package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.ForficataListener;

class AsynchronousBlueCoveWiimoteConnector extends BlueCoveWiimoteConnector {

	private static final Runnable NULL_OPERATION = new Runnable() {
		@Override
		public void run() {
		}
	};

	public AsynchronousBlueCoveWiimoteConnector(int maximumNumberOfWiimotes) {
		super(maximumNumberOfWiimotes);
	}

	@Override
	public void run(final ForficataListener listener) {
		runAsyncSearch(listener, NULL_OPERATION);
	}

}
