package com.github.awvalenti.bauhinia.coronata;

class TimeoutCoundown {

	private final long startTime = System.nanoTime();

	private final int timeout;

	TimeoutCoundown(int timeout) {
		this.timeout = timeout;
	}

	boolean finished() {
		long inSeconds = (long) 1e9;

		return (System.nanoTime() - startTime) / inSeconds >= timeout;
	}

}
