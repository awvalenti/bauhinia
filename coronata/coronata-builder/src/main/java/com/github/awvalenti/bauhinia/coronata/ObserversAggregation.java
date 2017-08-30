package com.github.awvalenti.bauhinia.coronata;

/**
 * Just an aggregation of composite observers in a single object.
 */
class ObserversAggregation {

	final CompositeButtonObserver button = new CompositeButtonObserver();

	final CompositeConnectionObserver connection = new CompositeConnectionObserver();

	final CompositeDisconnectionObserver disconnection = new CompositeDisconnectionObserver();

	final CompositeLifecycleEventsObserver lifecycleEvents = new CompositeLifecycleEventsObserver();

	final CompositeLifecycleStateObserver lifecycleState = new CompositeLifecycleStateObserver();

	final CompositePhaseObserver phase = new CompositePhaseObserver();

	final CompositeErrorObserver error = new CompositeErrorObserver();

}
