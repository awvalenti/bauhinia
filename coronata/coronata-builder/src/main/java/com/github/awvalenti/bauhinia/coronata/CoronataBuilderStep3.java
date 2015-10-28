package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.WiimoteConnector;
import com.github.awvalenti.bauhinia.coronata.listeners.CoronataButtonListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiimoteConnectionObserver;

public interface CoronataBuilderStep3 {

	CoronataBuilderStep3 wiimoteConnectionObserver(CoronataWiimoteConnectionObserver o);

	CoronataBuilderStep3 fullObserver(CoronataObserver o);

	CoronataBuilderStep3 phaseStateObserver(CoronataPhaseObserver o);

	CoronataBuilderStep3 connectionStateObserver(CoronataConnectionStateObserver o);

	CoronataBuilderStep3 observers(CoronataObserver... observers);

	CoronataBuilderStep3 buttonListener(CoronataButtonListener listener);

	WiimoteConnector build();

}
