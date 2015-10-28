package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.listeners.CoronataButtonListener;
import com.github.awvalenti.bauhinia.forficata.observers.CoronataObserver;
import com.github.awvalenti.bauhinia.forficata.observers.CoronataPhaseObserver;
import com.github.awvalenti.bauhinia.forficata.observers.CoronataWiimoteConnectionObserver;
import com.github.awvalenti.bauhinia.forficata.observers.CoronataConnectionStateObserver;

public interface CoronataBuilderStep3 {

	CoronataBuilderStep3 wiimoteConnectionObserver(CoronataWiimoteConnectionObserver o);

	CoronataBuilderStep3 fullObserver(CoronataObserver o);

	CoronataBuilderStep3 phaseStateObserver(CoronataPhaseObserver o);

	CoronataBuilderStep3 connectionStateObserver(CoronataConnectionStateObserver o);

	CoronataBuilderStep3 observers(CoronataObserver... observers);

	CoronataBuilderStep3 buttonListener(CoronataButtonListener listener);

	WiimoteConnector build();

}
