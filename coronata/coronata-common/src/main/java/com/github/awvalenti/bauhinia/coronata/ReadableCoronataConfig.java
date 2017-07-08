package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;

interface ReadableCoronataConfig {

	WiiRemoteFullListener getWiiRemoteListener();

	int getWiiRemotesExpected();

	boolean isSynchronous();

	CoronataFullObserver getCoronataObserver();

}
