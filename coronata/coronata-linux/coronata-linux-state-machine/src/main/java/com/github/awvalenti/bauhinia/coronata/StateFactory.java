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
		return new LoadLibraryState(this, leObserver);
	}

	@SuppressWarnings("hiding")
	State startInquiry(BlueCoveLibraryFacade blueCoveLib) {
		this.blueCoveLib = blueCoveLib;
		return startInquiry();
	}

	State startInquiry() {
		return new StartInquiryState(this, blueCoveLib);
	}

	State waitForInquiry(BluetoothInquiryResult inquiryResult) {
		return new WaitForInquiryState(this, inquiryResult, blueCoveLib);
	}

	State bluetoothException(BluetoothStateException e) {
		return new BluetoothExceptionState(this, leObserver, e);
	}

	@SuppressWarnings("hiding")
	State informCandidates(CandidatesQueue candidates) {
		this.candidates = candidates;
		return new InformCandidatesState(this, leObserver, candidates);
	}

	State identifyNextDevice() {
		return new IdentifyNextDeviceState(this, candidates);
	}

	State identificationRejected(String btAddress) {
		return new IdentificationRejectedState(this, leObserver, btAddress);
	}

	State identifiedAsNonWiiRemote(String btAddress) {
		return new IdentifiedAsNonWiiRemoteState(this, leObserver, btAddress);
	}

	State connect(String btAddress) {
		return new ConnectState(this, leObserver, btAddress, wiiRemoteFactory);
	}

	State connectionRejected(String btAddress) {
		return new ConnectionRejectedState(this, leObserver, btAddress);
	}

	State connectionAccepted(BlueCoveWiiRemote wiiRemote) {
		return new ConnectionAcceptedState(this, connectionsCounter, wiiRemote);
	}

	State finish() {
		return new FinishState();
	}

}
