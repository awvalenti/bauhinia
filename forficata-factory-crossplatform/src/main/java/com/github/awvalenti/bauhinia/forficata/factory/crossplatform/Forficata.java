package com.github.awvalenti.bauhinia.forficata.factory.crossplatform;

import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnector;

public abstract class Forficata {

	public static WiimoteConnector asyncConnector() {
		return new ForficataFactoryCrossplatform().createConnector(1);
	}

}
