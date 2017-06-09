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

	private final CompositeObserver compo = new CompositeObserver();
	
	private void moveToPhase(CoronataPhase coronataPhase) {
		compo.running(coronataPhase);
		currentPhase = coronataPhase;
	}

	@Override
	public void coronataStarted() {
		compo.coronataStarted();
		compo.enteredInProcessState();
		compo.starting();
		moveToPhase(LOAD_LIBRARY);
	}

	@Override
	public void libraryLoaded() {
		compo.success(LOAD_LIBRARY);
		compo.libraryLoaded();
		moveToPhase(FIND_WII_REMOTE);
	}

	@Override
	public void searchStarted() {
		compo.searchStarted();
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		compo.bluetoothDeviceFound(address, deviceClass);
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		compo.deviceRejectedIdentification(address, deviceClass);
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		compo.deviceIdentifiedAsNotWiiRemote(address, deviceClass);
	}

	@Override
	public void wiiRemoteIdentified() {
		identified = true;
		compo.wiiRemoteIdentified();
		compo.success(FIND_WII_REMOTE);
		moveToPhase(CONNECT_TO_WII_REMOTE);
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		connected = true;
		compo.success(CONNECT_TO_WII_REMOTE);
		compo.enteredConnectedState();
		compo.wiiRemoteConnected(wiiRemote);
	}

	@Override
	public void searchFinished() {
		compo.searchFinished();
		
		if (!connected) {
			compo.enteredIdleState();
		}
		// TODO Provide failure information
		if (!identified) {
			compo.failure(FIND_WII_REMOTE);
		}

	}

	@Override
	public void errorOccurred(CoronataException e) {
		compo.errorOccurred(e);
		compo.enteredIdleState();
		compo.failure(currentPhase);
	}

	@Override
	public void buttonPressed(WiiRemoteButton button) {
		compo.buttonPressed(button);
	}

	@Override
	public void buttonReleased(WiiRemoteButton button) {
		compo.buttonReleased(button);
	}

	@Override
	public void wiiRemoteDisconnected() {
		compo.wiiRemoteDisconnected();
		
		// ########################################################
		// TODO Set correct library state on Wii Remote disconnection
		// ########################################################
	}

	public void addButtonListener(WiiRemoteButtonListener l) {
		compo.addButtonListener(l);
	}

	public void addDisconnectionListener(WiiRemoteDisconnectionListener l) {
		compo.addDisconnectionListener(l);
	}

	public void addConnectionObserver(CoronataWiiRemoteConnectionObserver o) {
		compo.addConnectionObserver(o);
	}

	public void addConnectionStateObserver(CoronataConnectionStateObserver o) {
		// XXX Should not have to call this here; calling this triggers the
		// configuration of initial state on observer. But the observer
		// should configure itself upon construction instead of depending
		// on this method being called.
		o.enteredIdleState();

		compo.addConnectionStateObserver(o);
	}

	public void addPhaseStateObserver(CoronataPhaseObserver o) {
		compo.addPhaseStateObserver(o);
	}

	public void addFullObserver(CoronataFullObserver o) {
		compo.addFullObserver(o);
	}

}
