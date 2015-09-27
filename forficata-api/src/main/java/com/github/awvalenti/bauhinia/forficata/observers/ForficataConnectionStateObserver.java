package com.github.awvalenti.bauhinia.forficata.observers;

import com.github.awvalenti.bauhinia.forficata.ConnectionState;

public interface ForficataConnectionStateObserver {

	void connectionStateChanged(ConnectionState state);

}
