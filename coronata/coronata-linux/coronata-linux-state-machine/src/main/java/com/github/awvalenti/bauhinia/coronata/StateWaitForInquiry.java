package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.State.RunPolicy.*;

class StateWaitForInquiry extends
		State {

	private final StateFactory states;

	private InquiryResult inquiryResult;
	private BlueCoveLibraryFacade blueCoveLib;

	StateWaitForInquiry(StateFactory states, InquiryResult inquiryResult,
			BlueCoveLibraryFacade blueCoveLib) {
		super(STOP_IF_REQUESTED_OR_TIMEOUT);
		this.states = states;
		this.inquiryResult = inquiryResult;
		this.blueCoveLib = blueCoveLib;
	}

	@Override
	void cleanUpIfDidntRun() {
		blueCoveLib.stopSearch();
	}

	@Override
	State run() {
		if (inquiryResult.isFinished()) {
			return states.informCandidates(inquiryResult.getCandidateDevices());
		}

		try {
			// Hasn't finished; will sleep and repeat this state until finished
			Thread.sleep(10);
			return this;

		} catch (InterruptedException e) {
			// Won't happen
			throw new RuntimeException(e);
		}
	}

}
