package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.listeners.CoronataWiimoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataObserver;

interface ReadableCoronataConfig {

	CoronataWiimoteFullListener getWiimoteListener();

	int getWiimotesExpected();

	boolean isSynchronous();

	CoronataObserver getCoronataObserver();

}
