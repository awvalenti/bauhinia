package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class StateFactory {

	private final CoronataLifecycleEventsObserver leObserver;
	private final WiiRemoteFactory wiiRemoteFactory;
	private final Counter connectionsCounter;

	private BlueCoveLibraryFacade blueCoveLib;
	private CandidatesQueue candidates;

	StateFactory(CoronataLifecycleEventsObserver leObserver,
			WiiRemoteFactory wiiRemoteFactory, Counter connectionsCounter) {
		this.leObserver = leObserver;
		this.wiiRemoteFactory = wiiRemoteFactory;
		this.connectionsCounter = connectionsCounter;
	}

	State loadLibrary() {
		return new StateLoadLibrary(this, leObserver);
	}

	@SuppressWarnings("hiding")
	State startInquiry(BlueCoveLibraryFacade blueCoveLib) {
		this.blueCoveLib = blueCoveLib;
		return startInquiry();
	}

	State startInquiry() {
		return new StateStartInquiry(this, leObserver, blueCoveLib);
	}

	State waitForInquiry(InquiryResult inquiryResult) {
		return new StateWaitForInquiry(this, inquiryResult, blueCoveLib);
	}

	State bluetoothException(BluetoothStateException e) {
		return new StateBluetoothException(this, leObserver, e);
	}

	@SuppressWarnings("hiding")
	State informCandidates(CandidatesQueue candidates) {
		this.candidates = candidates;
		return new StateInformCandidates(this, leObserver, candidates);
	}

	State identifyNextDevice() {
		return new StateIdentifyNextDevice(this, candidates, wiiRemoteFactory);
	}

	State identificationRejected(String btAddress) {
		return new StateIdentificationRejected(this, leObserver, btAddress);
	}

	State identifiedAsNonWiiRemote(String btAddress) {
		return new StateIdentifiedAsNonWiiRemote(this, leObserver, btAddress);
	}

	State connect(String btAddress) {
		return new StateConnect(this, leObserver, btAddress, wiiRemoteFactory);
	}

	State connectionRejected(String btAddress) {
		return new StateConnectionRejected(this, leObserver, btAddress);
	}

	State connectionAccepted(BlueCoveWiiRemote wiiRemote) {
		return new StateConnectionAccepted(this, connectionsCounter, wiiRemote);
	}

	State finish() {
		return new StateFinish();
	}

}
