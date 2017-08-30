package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.*;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class EventsMediator implements CoronataLifecycleEventsObserver, CoronataButtonObserver {

	private CoronataPhase currentPhase;
	private boolean identified = false;
	private boolean connected = false;

	private final ObserversAggregation observers;

	public EventsMediator(ObserversAggregation observers) {
		this.observers = observers;
	}

	private void moveToPhase(CoronataPhase coronataPhase) {
		observers.phase.running(coronataPhase);
		currentPhase = coronataPhase;
	}

	@Override
	public void coronataStarted() {
		observers.lifecycleEvents.coronataStarted();
		observers.lifecycleState.enteredInProcessState();
		observers.phase.starting();
		moveToPhase(LOAD_LIBRARY);
	}

	@Override
	public void libraryLoaded() {
		observers.lifecycleEvents.libraryLoaded();
		observers.phase.success(LOAD_LIBRARY);
		moveToPhase(FIND_WII_REMOTE);
	}

	@Override
	public void searchStarted() {
		observers.lifecycleEvents.searchStarted();
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		observers.lifecycleEvents.bluetoothDeviceFound(address, deviceClass);
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		observers.lifecycleEvents.deviceRejectedIdentification(address, deviceClass);
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		observers.lifecycleEvents.deviceIdentifiedAsNotWiiRemote(address, deviceClass);
	}

	@Override
	public void wiiRemoteIdentified() {
		identified = true;
		observers.lifecycleEvents.wiiRemoteIdentified();
		observers.phase.success(FIND_WII_REMOTE);
		moveToPhase(CONNECT_TO_WII_REMOTE);
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
		}
		// TODO Provide failure information
		if (!identified) {
			observers.phase.failure(FIND_WII_REMOTE);
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
		identified = false;
		connected = false;
		observers.lifecycleEvents.disconnected();
		observers.disconnection.disconnected();
		observers.lifecycleState.enteredIdleState();

		// TODO starting may be an odd name in this context
		observers.phase.starting();
	}

}
