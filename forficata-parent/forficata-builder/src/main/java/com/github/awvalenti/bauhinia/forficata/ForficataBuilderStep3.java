package com.github.awvalenti.bauhinia.forficata;

import com.github.awvalenti.bauhinia.forficata.listeners.ForficataButtonListener;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataConnectionStateObserver;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataPhaseObserver;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataWiimoteConnectionObserver;

public interface ForficataBuilderStep3 {

	ForficataBuilderStep3 wiimoteConnectionObserver(ForficataWiimoteConnectionObserver o);

	ForficataBuilderStep3 fullObserver(ForficataObserver o);

	ForficataBuilderStep3 phaseStateObserver(ForficataPhaseObserver O);

	ForficataBuilderStep3 connectionStateObserver(ForficataConnectionStateObserver o);

	ForficataBuilderStep3 observers(ForficataObserver... observers);

	ForficataBuilderStep3 buttonListener(ForficataButtonListener listener);

	WiimoteConnector build();

}
