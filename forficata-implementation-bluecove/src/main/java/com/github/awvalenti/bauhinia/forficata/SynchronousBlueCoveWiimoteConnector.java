package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import com.github.awvalenti.bauhinia.forficata.api.ForficataListener;

public class SynchronousBlueCoveWiimoteConnector extends BlueCoveWiimoteConnector {

	public SynchronousBlueCoveWiimoteConnector(int maximumNumberOfWiimotes) {
		super(maximumNumberOfWiimotes);
	}

	@Override
	public void run(final ForficataListener listener) {
		try {
			final Object monitor = new Object();

			runAsyncSearch(listener, new Runnable() {
				@Override
				public void run() {
					synchronized (monitor) {
						monitor.notify();
					}
				}
			});

			synchronized (monitor) {
				monitor.wait();
			}

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
