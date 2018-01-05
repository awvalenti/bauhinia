package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;

class StartInquiryState extends State {

	private final StateFactory states;

	private final BlueCoveLibraryFacade blueCoveLib;

	StartInquiryState(StateFactory states, BlueCoveLibraryFacade blueCoveLib) {
		this.states = states;
		this.blueCoveLib = blueCoveLib;
	}

	@Override
	State run() {
		try {
			BluetoothInquiryResult inquiryResult = new BluetoothInquiryResult();
			blueCoveLib.startAsynchronousSearch(inquiryResult);
			return states.waitForInquiry(inquiryResult);

		} catch (BluetoothStateException e) {
			return states.bluetoothException(e);
		}
	}

	@Override
	void cleanUpIfStoppedHere() {
		blueCoveLib.stopSearch();
	}

}
