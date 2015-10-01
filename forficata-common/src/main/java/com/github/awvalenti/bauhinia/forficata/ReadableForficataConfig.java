package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.listeners.ForficataWiimoteFullListener;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;

interface ReadableForficataConfig {

	ForficataWiimoteFullListener getWiimoteListener();

	int getWiimotesExpected();

	boolean isSynchronous();

	ForficataObserver getForficataObserver();

}
