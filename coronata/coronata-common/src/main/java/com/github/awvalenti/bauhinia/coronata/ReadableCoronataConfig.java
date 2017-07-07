package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;

interface ReadableCoronataConfig {

	CoronataButtonObserver getButtonObserver();

	int getWiiRemotesExpected();

	// TODO Change to isSingleThreaded or something like that
	boolean isSynchronous();

	CoronataLifecycleEventsObserver getLifecycleEventsObserver();

}
