package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.*;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class EventsMediator implements
		CoronataLifecycleEventsObserver,
		CoronataButtonObserver {

	private CoronataPhase currentPhase;
	private boolean connected;

	private final ObserversAggregation observers;

	public EventsMediator(ObserversAggregation observers) {
		this.observers = observers;
	}

	private void moveToPhase(CoronataPhase phase) {
		currentPhase = phase;
		observers.phase.running(phase);
	}

	@Override
	public void coronataStarted() {
		connected = false;
		observers.lifecycleEvents.coronataStarted();
		observers.lifecycleState.enteredInProcessState();
		observers.phase.reset();
		moveToPhase(LOAD_LIBRARY);
	}

	@Override
	public void searchStarted(boolean isWindows) {
		observers.lifecycleEvents.searchStarted(isWindows);
		observers.phase.success(LOAD_LIBRARY);
		moveToPhase(FIND_WII_REMOTE);
	}

	@Override
	public void bluetoothDeviceFound(String btAddress, String deviceClass) {
		observers.lifecycleEvents.bluetoothDeviceFound(btAddress, deviceClass);
	}

	@Override
	public void identificationRejected(String btAddress) {
		observers.lifecycleEvents.identificationRejected(btAddress);
	}

	@Override
	public void identifiedAsNonWiiRemote(String btAddress) {
		observers.lifecycleEvents.identifiedAsNonWiiRemote(btAddress);
	}

	@Override
	public void identifiedAsWiiRemote(String btAddressOrNull) {
		observers.lifecycleEvents.identifiedAsWiiRemote(btAddressOrNull);
		observers.phase.success(FIND_WII_REMOTE);
		moveToPhase(CONNECT_TO_WII_REMOTE);
	}

	@Override
	public void connectionRejected(String btAddress) {
		observers.lifecycleEvents.connectionRejected(btAddress);
		observers.phase.failure(CONNECT_TO_WII_REMOTE);
		moveToPhase(FIND_WII_REMOTE);
	}

	@Override
	public void connected(CoronataWiiRemote wiiRemote) {
		connected = true;
		observers.lifecycleEvents.connected(wiiRemote);
		observers.phase.success(CONNECT_TO_WII_REMOTE);
		observers.lifecycleState.enteredConnectedState();
		observers.connection.connected(wiiRemote);
	}

	@Override
	public void searchFinished() {
		observers.lifecycleEvents.searchFinished();

		if (!connected) {
			observers.lifecycleState.enteredIdleState();
			observers.phase.failure(currentPhase);
		}
	}

	@Override
	public void errorOccurred(CoronataException e) {
		observers.lifecycleEvents.errorOccurred(e);
		observers.lifecycleState.enteredIdleState();
		observers.phase.failure(currentPhase);
		observers.error.errorOccurred(e);
	}

	@Override
	public void buttonPressed(CoronataWiiRemoteButton button) {
		observers.button.buttonPressed(button);
	}

	@Override
	public void buttonReleased(CoronataWiiRemoteButton button) {
		observers.button.buttonReleased(button);
	}

	@Override
	public void disconnected() {
		connected = false;
		observers.lifecycleEvents.disconnected();
		observers.disconnection.disconnected();
		observers.lifecycleState.enteredIdleState();
		observers.phase.reset();
	}

}
