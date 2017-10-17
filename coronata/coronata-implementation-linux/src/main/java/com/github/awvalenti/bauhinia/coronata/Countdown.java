package com.github.awvalenti.bauhinia.coronata;

class Countdown {

	private final long startTime = System.nanoTime();

	private final int timeout;

	Countdown(int timeout) {
		this.timeout = timeout;
	}

	boolean finished() {
		long inSeconds = (long) 1e9;

		return (System.nanoTime() - startTime) / inSeconds >= timeout;
	}

}
