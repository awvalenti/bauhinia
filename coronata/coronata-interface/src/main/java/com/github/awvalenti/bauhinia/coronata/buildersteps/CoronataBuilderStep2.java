package com.github.awvalenti.bauhinia.coronata.buildersteps;

import com.github.awvalenti.bauhinia.coronata.Coronata;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataErrorObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;

public interface CoronataBuilderStep2 {

	CoronataBuilderStep2 onConnection(CoronataConnectionObserver o);

	CoronataBuilderStep2 onDisconnection(CoronataDisconnectionObserver l);

	CoronataBuilderStep2 onButton(CoronataButtonObserver listener);

	CoronataBuilderStep2 onLifecycleState(CoronataLifecycleStateObserver o);

	CoronataBuilderStep2 onLifecycleEvents(CoronataLifecycleEventsObserver o);

	CoronataBuilderStep2 onPhase(CoronataPhaseObserver o);
	
	CoronataBuilderStep2 onError(CoronataErrorObserver o);

	Coronata endConfig();

}
