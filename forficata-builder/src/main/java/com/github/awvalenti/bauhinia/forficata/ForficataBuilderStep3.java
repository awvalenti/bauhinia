package com.github.awvalenti.bauhinia.forficata;

public interface ForficataBuilderStep3 {

	ForficataBuilderStep3 eventListener(ForficataEventListener listener);

	ForficataBuilderStep3 phaseListener(ForficataPhaseListener listener);

	WiimoteConnector build();

}
