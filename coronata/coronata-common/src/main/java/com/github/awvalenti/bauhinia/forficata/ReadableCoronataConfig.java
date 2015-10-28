package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.listeners.CoronataWiimoteFullListener;
import com.github.awvalenti.bauhinia.forficata.observers.CoronataObserver;

interface ReadableCoronataConfig {

	CoronataWiimoteFullListener getWiimoteListener();

	int getWiimotesExpected();

	boolean isSynchronous();

	CoronataObserver getForficataObserver();

}
