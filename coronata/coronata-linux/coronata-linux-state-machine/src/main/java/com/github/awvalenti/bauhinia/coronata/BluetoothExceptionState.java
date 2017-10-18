package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.State.RunPolicy.*;

import javax.bluetooth.BluetoothStateException;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class BluetoothExceptionState extends State {

	private final StateFactory states;

	private final BlueCoveExceptionFactory exceptionFactory =
			new BlueCoveExceptionFactory();

	private final CoronataLifecycleEventsObserver leObserver;
	private final BluetoothStateException exception;

	BluetoothExceptionState(StateFactory states,
			CoronataLifecycleEventsObserver leObserver,
			BluetoothStateException exception) {
		super(STOP_IF_REQUESTED_OR_TIMEOUT);
		this.states = states;
		this.leObserver = leObserver;
		this.exception = exception;
	}

	@Override
	State run() {
		leObserver.errorOccurred(exceptionFactory.correspondingTo(exception));
		return states.finish();
	}

}
