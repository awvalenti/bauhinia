package com.github.awvalenti.forficata.api;

public interface WiimoteConnector {

	void searchAndConnect(WiimoteConnectedCallback callback);

	void connectToWiimoteAt(String bluetoothAddress, WiimoteConnectedCallback callback);

}
