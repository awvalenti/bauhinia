package com.github.awvalenti.bauhinia.coronata;

class AllObservers {

	final CompositeButtonListener buttonListeners = new CompositeButtonListener();
	
	final CompositeConnectionStateObserver connectionStateObservers = new CompositeConnectionStateObserver();
	
	final CompositeDisconnectionListener disconnectionListeners = new CompositeDisconnectionListener();
	
	final CompositeFullObserver fullObservers = new CompositeFullObserver();
	
	final CompositePhaseObserver phaseObservers = new CompositePhaseObserver();
	
	final CompositeConnectionObserver connectionObservers = new CompositeConnectionObserver();

}
