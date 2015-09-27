package com.github.awvalenti.bauhinia.forficata;

import java.util.ArrayList;
import java.util.List;

class ForficataConfiguration {

	boolean synchronous;

	int wiimotesExpected;

	ForficataWiimoteListener wiimoteListener;

	List<ForficataEventListener> listeners = new ArrayList<ForficataEventListener>();

	public void addForficataEventListener(ForficataEventListener listener) {
		listeners.add(listener);
	}

	public ForficataEventListener getForficataEventListener() {
		// XXX Make a composite, decorator or something similar
		return listeners.get(0);
	}

}
