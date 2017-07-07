package com.github.awvalenti.bauhinia.coronata;

/**
 * Just an aggreagtion of composite observers in a single object.
 * Not idiomatic Java, since it has fields with package-level access.
 * That, however, makes usage easier, and usage is very local.
 */
class ObserversAggregation {

	final CompositeButtonObserver button = new CompositeButtonObserver();

	final CompositeConnectionObserver connection = new CompositeConnectionObserver();

	final CompositeDisconnectionObserver disconnection = new CompositeDisconnectionObserver();

	final CompositeLifecycleEventsObserver lifecycleEvents = new CompositeLifecycleEventsObserver();

	final CompositeLifecycleStateObserver lifecycleState = new CompositeLifecycleStateObserver();

	final CompositePhaseObserver phase = new CompositePhaseObserver();

}
