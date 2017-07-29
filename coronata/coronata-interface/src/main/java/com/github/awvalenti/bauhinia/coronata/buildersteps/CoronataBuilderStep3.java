package com.github.awvalenti.bauhinia.coronata.buildersteps;

import com.github.awvalenti.bauhinia.coronata.Coronata;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;

public interface CoronataBuilderStep3 {

	CoronataBuilderStep3 onConnection(CoronataConnectionObserver o);

	CoronataBuilderStep3 onDisconnection(CoronataDisconnectionObserver l);

	CoronataBuilderStep3 onButton(CoronataButtonObserver listener);

	CoronataBuilderStep3 onLifecycleState(CoronataLifecycleStateObserver o);

	CoronataBuilderStep3 onPhase(CoronataPhaseObserver o);

	CoronataBuilderStep3 onLifecycleEvents(CoronataLifecycleEventsObserver o);

	Coronata endConfig();

}
