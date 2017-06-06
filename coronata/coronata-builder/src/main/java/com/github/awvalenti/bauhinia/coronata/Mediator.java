package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.CONNECT_TO_WII_REMOTE;
import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.FIND_WII_REMOTE;
import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.LOAD_LIBRARY;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteButtonListener;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteDisconnectionListener;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiiRemoteConnectionObserver;

public class Mediator implements CoronataFullObserver, WiiRemoteFullListener {

	private CompositeListener compositeListener = new CompositeListener();
	private CompositeObserver compositeObserver = new CompositeObserver();

	private CoronataPhase currentPhase;
	private boolean identified = false;
	private boolean connected = false;

	private List<WiiRemoteButtonListener> buttonListeners = new ArrayList<WiiRemoteButtonListener>();
	private List<WiiRemoteDisconnectionListener> disconnectionListeners = new ArrayList<WiiRemoteDisconnectionListener>();
	private List<CoronataWiiRemoteConnectionObserver> connectionObservers = new ArrayList<CoronataWiiRemoteConnectionObserver>();
	private List<CoronataConnectionStateObserver> connectionStateObservers = new ArrayList<CoronataConnectionStateObserver>();
	private List<CoronataPhaseObserver> phaseStateObservers = new ArrayList<CoronataPhaseObserver>();

	public void addListener(WiiRemoteFullListener l) {
		compositeListener.addListener(l);
	}

	public void addObserver(CoronataFullObserver o) {
		compositeObserver.add(o);
	}

	@Override
	public void coronataStarted() {
		compositeObserver.coronataStarted();
		for (CoronataConnectionStateObserver o : connectionStateObservers) {
			o.enteredInProcessState();
		}
		for (CoronataPhaseObserver o : phaseStateObservers) {
			o.starting();
		}
		moveToPhase(LOAD_LIBRARY);
	}

	@Override
	public void libraryLoaded() {
		compositeObserver.libraryLoaded();
		for (CoronataPhaseObserver o : phaseStateObservers) {
			o.success(LOAD_LIBRARY);
		}
		moveToPhase(FIND_WII_REMOTE);
	}

	@Override
	public void searchStarted() {
		compositeObserver.searchStarted();
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		compositeObserver.bluetoothDeviceFound(address, deviceClass);
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		compositeObserver.deviceRejectedIdentification(address, deviceClass);
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		compositeObserver.deviceIdentifiedAsNotWiiRemote(address, deviceClass);
	}

	@Override
	public void wiiRemoteIdentified() {
		compositeObserver.wiiRemoteIdentified();
		identified = true;
		for (CoronataPhaseObserver o : phaseStateObservers) {
			o.success(FIND_WII_REMOTE);
		}
		moveToPhase(CONNECT_TO_WII_REMOTE);
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		connected = true;
		for (CoronataWiiRemoteConnectionObserver o : connectionObservers) {
			o.wiiRemoteConnected(wiiRemote);
		}
		for (CoronataConnectionStateObserver o : connectionStateObservers) {
			o.enteredConnectedState();
		}
		for (CoronataPhaseObserver o : phaseStateObservers) {
			o.success(CONNECT_TO_WII_REMOTE);
		}
	}

	@Override
	public void searchFinished() {
		compositeObserver.searchFinished();
		
		if (!connected) {
			for (CoronataConnectionStateObserver o : connectionStateObservers) {
				o.enteredIdleState();
			}
		}
		// TODO Provide failure information
		if (!identified) {
			for (CoronataPhaseObserver o : phaseStateObservers) {
				o.failure(FIND_WII_REMOTE);
			}
		}

	}

	@Override
	public void errorOccurred(CoronataException e) {
		compositeObserver.errorOccurred(e);
		
		for (CoronataConnectionStateObserver o : connectionStateObservers) {
			o.enteredIdleState();
		}
		for (CoronataPhaseObserver o : phaseStateObservers) {
			// TODO Provide failure information
			o.failure(currentPhase);
		}

	}

	@Override
	public void buttonPressed(WiiRemoteButton button) {
		for (WiiRemoteButtonListener l : buttonListeners) {
			l.buttonPressed(button);
		}
	}

	@Override
	public void buttonReleased(WiiRemoteButton button) {
		for (WiiRemoteButtonListener l : buttonListeners) {
			l.buttonReleased(button);
		}
	}

	@Override
	public void wiiRemoteDisconnected() {
		for (WiiRemoteDisconnectionListener l : disconnectionListeners) {
			l.wiiRemoteDisconnected();
		}
		// ########################################################
		// TODO Fix library state on wii remote disconnection
		// ########################################################
	}

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
		phaseStateObservers.add(o);
	}

	public void addFullObserver(CoronataFullObserver o) {
		compositeObserver.add(o);
	}

	private void moveToPhase(CoronataPhase coronataPhase) {
		for (CoronataPhaseObserver o : phaseStateObservers) {
			o.running(coronataPhase);
		}
		currentPhase = coronataPhase;
	}

}
