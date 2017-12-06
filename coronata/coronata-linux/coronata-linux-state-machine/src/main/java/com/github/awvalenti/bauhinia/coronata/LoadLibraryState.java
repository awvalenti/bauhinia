package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class LoadLibraryState extends State {

	private final StateFactory states;

	private final CoronataLifecycleEventsObserver leObserver;

	LoadLibraryState(StateFactory states,
			CoronataLifecycleEventsObserver leObserver) {
		this.states = states;
		this.leObserver = leObserver;
	}

	@Override
	State run() {
		try {
			BlueCoveLibraryFacade blueCoveLib = new BlueCoveLibraryFacade();
			leObserver.libraryLoadedSearchStarted();
			return states.startInquiry(blueCoveLib);

		} catch (BluetoothStateException e) {
			return states.bluetoothException(e);
		}
	}
}
