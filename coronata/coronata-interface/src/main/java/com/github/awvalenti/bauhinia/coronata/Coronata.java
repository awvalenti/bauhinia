package com.github.awvalenti.bauhinia.coronata;

public interface Coronata {

	void startConnectionProcess();

	/**
	 * Affects Linux implementation only. On Windows, since connection process
	 * is fast, user is very unlikely to be able to stop it in the middle.
	 */
	void stopConnectionProcessIfActive();

}
