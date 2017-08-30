package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;

interface ReadableCoronataConfig {

	int getWiiRemotesExpected();

	CoronataButtonObserver getButtonObserver();

	CoronataLifecycleEventsObserver getLifecycleEventsObserver();

}
