package com.github.awvalenti.bauhinia.coronata;

public interface CoronataConnectionProcess {

	void start();

	/**
	 * Works on Linux, whose connection process takes about 5 to 30 seconds.
	 * On Windows, has no effect, for two reasons: Windows connection process
	 * 1) is controlled by an underlying library; 2) usually takes less than a
	 * second to run.
	 */
	void cancel();

}
