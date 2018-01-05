package com.github.awvalenti.bauhinia.coronata;

class WaitForInquiryState extends State {

	private final StateFactory states;

	private final BluetoothInquiryResult inquiryResult;
	private final BlueCoveLibraryFacade blueCoveLib;

	WaitForInquiryState(StateFactory states,
			BluetoothInquiryResult inquiryResult,
			BlueCoveLibraryFacade blueCoveLib) {
		this.states = states;
		this.inquiryResult = inquiryResult;
		this.blueCoveLib = blueCoveLib;
	}

	@Override
	State run() {
		if (inquiryResult.isFinished()) {
			return states.informCandidates(inquiryResult.getCandidateDevices());
		}

		// Hasn't finished; will sleep and repeat this state

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
		}

		return this;
	}

	@Override
	void cleanUpIfStoppedHere() {
		blueCoveLib.stopSearch();
	}

}
