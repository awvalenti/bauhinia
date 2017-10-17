package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class StateStartInquiry extends StateAbstractRunUnlessStopRequestedOrTimeout {

	private final StateFactory states;

	private final CoronataLifecycleEventsObserver leObserver;
	private final BlueCoveLibraryFacade blueCoveLib;

	StateStartInquiry(StateFactory states,
			CoronataLifecycleEventsObserver leObserver,
			BlueCoveLibraryFacade blueCoveLib) {
		this.states = states;
		this.leObserver = leObserver;
		this.blueCoveLib = blueCoveLib;
	}

	@Override
	State run() {
		leObserver.searchStarted();

		try {
			InquiryResult inquiryResult = new InquiryResult();
			blueCoveLib.startAsynchronousSearch(inquiryResult);
			return states.waitForInquiry(inquiryResult);

		} catch (BluetoothStateException e) {
			return states.bluetoothException(e);
		}
	}

}
