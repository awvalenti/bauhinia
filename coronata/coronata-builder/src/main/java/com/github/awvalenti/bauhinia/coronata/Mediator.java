package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.CONNECT_TO_WII_REMOTE;
import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.FIND_WII_REMOTE;
import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.LOAD_LIBRARY;

import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteButtonListener;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteDisconnectionListener;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiiRemoteConnectionObserver;

public class Mediator implements CoronataFullObserver, WiiRemoteFullListener {

	private CoronataPhase currentPhase;
	private boolean identified = false;
	private boolean connected = false;

	private final CompositeButtonListener buttonListeners = new CompositeButtonListener();
	private final CompositeConnectionStateObserver connectionStateObservers = new CompositeConnectionStateObserver();
	private final CompositeDisconnectionListener disconnectionListeners = new CompositeDisconnectionListener();
	private final CompositeFullObserver fullObservers = new CompositeFullObserver();
	private final CompositePhaseObserver phaseObservers = new CompositePhaseObserver();
	private final CompositeConnectionObserver connectionObservers = new CompositeConnectionObserver();

	public void addButtonListener(WiiRemoteButtonListener l) {
		buttonListeners.add(l);
	}

	public void addDisconnectionListener(WiiRemoteDisconnectionListener l) {
		disconnectionListeners.add(l);
	}

	public void addConnectionObserver(CoronataWiiRemoteConnectionObserver o) {
		connectionObservers.add(o);
	}

	public void addConnectionStateObserver(CoronataConnectionStateObserver o) {
		// XXX Should not have to call this here; calling this triggers the
		// configuration of initial state on observer. But the observer
		// should configure itself upon construction instead of depending
		// on this method being called.
		o.enteredIdleState();

		connectionStateObservers.add(o);
	}

	public void addPhaseStateObserver(CoronataPhaseObserver o) {
		phaseObservers.add(o);
	}

	public void addFullObserver(CoronataFullObserver o) {
		fullObservers.add(o);
	}

	private void moveToPhase(CoronataPhase coronataPhase) {
		phaseObservers.running(coronataPhase);
		currentPhase = coronataPhase;
	}

	@Override
	public void coronataStarted() {
		fullObservers.coronataStarted();
		connectionStateObservers.enteredInProcessState();
		phaseObservers.starting();
		moveToPhase(LOAD_LIBRARY);
	}

	@Override
	public void libraryLoaded() {
		fullObservers.libraryLoaded();
		phaseObservers.success(LOAD_LIBRARY);
		moveToPhase(FIND_WII_REMOTE);
	}

	@Override
	public void searchStarted() {
		fullObservers.searchStarted();
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		fullObservers.bluetoothDeviceFound(address, deviceClass);
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		fullObservers.deviceRejectedIdentification(address, deviceClass);
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		fullObservers.deviceIdentifiedAsNotWiiRemote(address, deviceClass);
	}

	@Override
	public void wiiRemoteIdentified() {
		identified = true;
		fullObservers.wiiRemoteIdentified();
		phaseObservers.success(FIND_WII_REMOTE);
		moveToPhase(CONNECT_TO_WII_REMOTE);
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		connected = true;
		fullObservers.wiiRemoteConnected(wiiRemote);
		phaseObservers.success(CONNECT_TO_WII_REMOTE);
		connectionStateObservers.enteredConnectedState();
	}

	@Override
	public void searchFinished() {
		fullObservers.searchFinished();

		if (!connected) {
			connectionStateObservers.enteredIdleState();
		}
		// TODO Provide failure information
		if (!identified) {
			phaseObservers.failure(FIND_WII_REMOTE);
		}

	}

	@Override
	public void errorOccurred(CoronataException e) {
		fullObservers.errorOccurred(e);
		connectionStateObservers.enteredIdleState();
		phaseObservers.failure(currentPhase);
	}

	@Override
	public void buttonPressed(WiiRemoteButton button) {
		buttonListeners.buttonPressed(button);
	}

	@Override
	public void buttonReleased(WiiRemoteButton button) {
		buttonListeners.buttonReleased(button);
	}

	@Override
	public void wiiRemoteDisconnected() {
		disconnectionListeners.wiiRemoteDisconnected();

		// ########################################################
		// TODO Set correct library state on Wii Remote disconnection
		// ########################################################
	}

}
