package com.github.awvalenti.bauhinia.coronata;

import com.github.awvalenti.bauhinia.coronata.CoronataConnector;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteButtonListener;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteDisconnectionListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiiRemoteConnectionObserver;

public interface CoronataBuilderStep3 {

	CoronataBuilderStep3 wiiRemoteConnectionObserver(CoronataWiiRemoteConnectionObserver o);

	CoronataBuilderStep3 disconnectionListener(WiiRemoteDisconnectionListener l);

	CoronataBuilderStep3 buttonListener(WiiRemoteButtonListener listener);

	CoronataBuilderStep3 connectionStateObserver(CoronataConnectionStateObserver o);

	CoronataBuilderStep3 phaseStateObserver(CoronataPhaseObserver o);

	CoronataBuilderStep3 fullObserver(CoronataFullObserver o);

	CoronataConnector build();

}
