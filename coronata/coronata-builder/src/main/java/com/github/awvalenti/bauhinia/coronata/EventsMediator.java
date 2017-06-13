package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.CONNECT_TO_WII_REMOTE;
import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.FIND_WII_REMOTE;
import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.LOAD_LIBRARY;

import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;

public class EventsMediator implements CoronataFullObserver, WiiRemoteFullListener {

	private CoronataPhase currentPhase;
	private boolean identified = false;
	private boolean connected = false;

	private final ObserversAggregation observers;

	public EventsMediator(ObserversAggregation observers) {
		this.observers = observers;
	}

	private void moveToPhase(CoronataPhase coronataPhase) {
		observers.phaseObservers.running(coronataPhase);
		currentPhase = coronataPhase;
	}

	@Override
	public void coronataStarted() {
		observers.fullObservers.coronataStarted();
		observers.connectionStateObservers.enteredInProcessState();
		observers.phaseObservers.starting();
		moveToPhase(LOAD_LIBRARY);
	}

	@Override
	public void libraryLoaded() {
		observers.fullObservers.libraryLoaded();
		observers.phaseObservers.success(LOAD_LIBRARY);
		moveToPhase(FIND_WII_REMOTE);
	}

	@Override
	public void searchStarted() {
		observers.fullObservers.searchStarted();
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		observers.fullObservers.bluetoothDeviceFound(address, deviceClass);
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		observers.fullObservers.deviceRejectedIdentification(address, deviceClass);
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		observers.fullObservers.deviceIdentifiedAsNotWiiRemote(address, deviceClass);
	}

	@Override
	public void wiiRemoteIdentified() {
		identified = true;
		observers.fullObservers.wiiRemoteIdentified();
		observers.phaseObservers.success(FIND_WII_REMOTE);
		moveToPhase(CONNECT_TO_WII_REMOTE);
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		connected = true;
		observers.fullObservers.wiiRemoteConnected(wiiRemote);
		observers.phaseObservers.success(CONNECT_TO_WII_REMOTE);
		observers.connectionStateObservers.enteredConnectedState();
		observers.connectionObservers.wiiRemoteConnected(wiiRemote);
	}

	@Override
	public void searchFinished() {
		observers.fullObservers.searchFinished();

		if (!connected) {
			observers.connectionStateObservers.enteredIdleState();
		}
		// TODO Provide failure information
		if (!identified) {
			observers.phaseObservers.failure(FIND_WII_REMOTE);
		}

	}

	@Override
	public void errorOccurred(CoronataException e) {
		observers.fullObservers.errorOccurred(e);
		observers.connectionStateObservers.enteredIdleState();
		observers.phaseObservers.failure(currentPhase);
	}

	@Override
	public void buttonPressed(WiiRemoteButton button) {
		observers.buttonListeners.buttonPressed(button);
	}

	@Override
	public void buttonReleased(WiiRemoteButton button) {
		observers.buttonListeners.buttonReleased(button);
	}

	@Override
	public void wiiRemoteDisconnected() {
		identified = false;
		connected = false;
		observers.fullObservers.wiiRemoteDisconnected();
		observers.disconnectionListeners.wiiRemoteDisconnected();
		observers.connectionStateObservers.enteredIdleState();

		// TODO starting may be an odd name in this context
		observers.phaseObservers.starting();
	}

}
