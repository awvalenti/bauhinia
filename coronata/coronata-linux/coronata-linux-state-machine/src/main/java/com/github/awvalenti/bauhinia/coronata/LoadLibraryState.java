package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.State.RunPolicy.*;

import javax.bluetooth.BluetoothStateException;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class LoadLibraryState extends State {

	private final StateFactory states;

	private final CoronataLifecycleEventsObserver leObserver;

	LoadLibraryState(StateFactory states,
			CoronataLifecycleEventsObserver leObserver) {
		super(STOP_IF_REQUESTED_OR_TIMEOUT);
		this.states = states;
		this.leObserver = leObserver;
	}

	@Override
	State run() {
		try {
			BlueCoveLibraryFacade blueCoveLib = new BlueCoveLibraryFacade();
			leObserver.libraryLoaded();
			return states.startInquiry(blueCoveLib);

		} catch (BluetoothStateException e) {
			return states.bluetoothException(e);
		}
	}
}
