package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.L2CAPConnection;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class StateFactory {

	private final CoronataLifecycleEventsObserver leObserver;
	private final CoronataButtonObserver buttonObserver;

	private final Counter connectionsCounter;

	private BlueCoveLibraryFacade blueCoveLib;
	private CandidatesQueue candidates;

	StateFactory(CoronataLifecycleEventsObserver leObserver,
			CoronataButtonObserver buttonObserver,
			Counter connectionsCounter) {
		this.leObserver = leObserver;
		this.buttonObserver = buttonObserver;
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

	State openControlPipe(String btAddress) {
		return new OpenControlPipeState(this, leObserver, btAddress);
	}

	State openDataPipe(String btAddress, L2CAPConnection controlPipe) {
		return new OpenDataPipeState(this, btAddress, controlPipe);
	}

	State connectionRejected(String btAddress) {
		return new ConnectionRejectedState(this, leObserver, btAddress);
	}

	State connectionAccepted(WiiRemoteConnection connection) {
		return new ConnectionAcceptedState(this, leObserver, connectionsCounter,
				connection, buttonObserver);
	}

	State finish() {
		return new FinishState();
	}

}
