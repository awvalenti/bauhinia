package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import com.github.awvalenti.bauhinia.forficata.api.ForficataCallback;

public class SynchronousBlueCoveWiimoteConnector extends BlueCoveWiimoteConnector {

	public SynchronousBlueCoveWiimoteConnector(int maximumNumberOfWiimotes) {
		super(maximumNumberOfWiimotes);
	}

	@Override
	public void run(final ForficataCallback callback) {
		try {

			final Object monitor = new Object();

			runAsyncSearch(callback, new Runnable() {
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
