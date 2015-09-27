package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;

interface ReadableForficataConfig {

	ForficataWiimoteListener getWiimoteListener();

	int getWiimotesExpected();

	boolean isSynchronous();

	ForficataObserver getForficataEventListener();

}
