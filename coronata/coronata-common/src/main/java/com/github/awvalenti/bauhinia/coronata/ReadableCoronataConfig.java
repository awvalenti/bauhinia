package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;

interface ReadableCoronataConfig {

	WiiRemoteFullListener getWiiRemoteListener();

	int getWiiRemotesExpected();

	// TODO Change to isSingleThreaded or something like that
	boolean isSynchronous();

	CoronataFullObserver getCoronataObserver();

}
