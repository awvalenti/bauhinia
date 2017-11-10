package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.State.RunPolicy.*;

import javax.bluetooth.BluetoothStateException;

class StartInquiryState extends State {

	private final StateFactory states;

	private final BlueCoveLibraryFacade blueCoveLib;

	StartInquiryState(StateFactory states, BlueCoveLibraryFacade blueCoveLib) {
		super(STOP_IF_REQUESTED_OR_TIMEOUT);
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

}
