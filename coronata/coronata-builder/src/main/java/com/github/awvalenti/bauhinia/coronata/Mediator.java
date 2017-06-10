package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.CONNECT_TO_WII_REMOTE;
import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.FIND_WII_REMOTE;
import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.LOAD_LIBRARY;

import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;

public class Mediator implements CoronataFullObserver, WiiRemoteFullListener {

	private CoronataPhase currentPhase;
	private boolean identified = false;
	private boolean connected = false;

	private final AllObservers all;

	public Mediator(AllObservers allObservers) {
		this.all = allObservers;
	}

	private void moveToPhase(CoronataPhase coronataPhase) {
		all.phaseObservers.running(coronataPhase);
		currentPhase = coronataPhase;
	}

	@Override
	public void coronataStarted() {
		all.fullObservers.coronataStarted();
		all.connectionStateObservers.enteredInProcessState();
		all.phaseObservers.starting();
		moveToPhase(LOAD_LIBRARY);
	}

	@Override
	public void libraryLoaded() {
		all.fullObservers.libraryLoaded();
		all.phaseObservers.success(LOAD_LIBRARY);
		moveToPhase(FIND_WII_REMOTE);
	}

	@Override
	public void searchStarted() {
		all.fullObservers.searchStarted();
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		all.fullObservers.bluetoothDeviceFound(address, deviceClass);
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		all.fullObservers.deviceRejectedIdentification(address, deviceClass);
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		all.fullObservers.deviceIdentifiedAsNotWiiRemote(address, deviceClass);
	}

	@Override
	public void wiiRemoteIdentified() {
		identified = true;
		all.fullObservers.wiiRemoteIdentified();
		all.phaseObservers.success(FIND_WII_REMOTE);
		moveToPhase(CONNECT_TO_WII_REMOTE);
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		connected = true;
		all.fullObservers.wiiRemoteConnected(wiiRemote);
		all.phaseObservers.success(CONNECT_TO_WII_REMOTE);
		all.connectionStateObservers.enteredConnectedState();
		all.connectionObservers.wiiRemoteConnected(wiiRemote);
	}

	@Override
	public void searchFinished() {
		all.fullObservers.searchFinished();

		if (!connected) {
			all.connectionStateObservers.enteredIdleState();
		}
		// TODO Provide failure information
		if (!identified) {
			all.phaseObservers.failure(FIND_WII_REMOTE);
		}

	}

	@Override
	public void errorOccurred(CoronataException e) {
		all.fullObservers.errorOccurred(e);
		all.connectionStateObservers.enteredIdleState();
		all.phaseObservers.failure(currentPhase);
	}

	@Override
	public void buttonPressed(WiiRemoteButton button) {
		all.buttonListeners.buttonPressed(button);
	}

	@Override
	public void buttonReleased(WiiRemoteButton button) {
		all.buttonListeners.buttonReleased(button);
	}

	@Override
	public void wiiRemoteDisconnected() {
		all.disconnectionListeners.wiiRemoteDisconnected();
		
		// XXX Replace null with Wii Remote, or remove parameter from signature
		all.connectionObservers.wiiRemoteDisconnected(null);

		// ########################################################
		// TODO Set correct library state on Wii Remote disconnection
		// ########################################################
	}

}
