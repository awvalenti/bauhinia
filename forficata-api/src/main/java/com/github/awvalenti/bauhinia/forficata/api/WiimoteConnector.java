package com.github.awvalenti.bauhinia.forficata.api;

public interface WiimoteConnector {

	void searchAndConnect(WiimoteConnectedCallback callback) throws ForficataException;

}
