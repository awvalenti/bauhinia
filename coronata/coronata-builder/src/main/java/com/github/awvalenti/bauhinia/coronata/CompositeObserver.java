package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteButtonListener;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteDisconnectionListener;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionStateObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiiRemoteConnectionObserver;

// TODO Split into 6 classes
public class CompositeObserver
		implements CoronataFullObserver, WiiRemoteFullListener, CoronataConnectionStateObserver, CoronataPhaseObserver {

	private List<WiiRemoteButtonListener> buttonListeners = new ArrayList<WiiRemoteButtonListener>();
	private List<WiiRemoteDisconnectionListener> disconnectionListeners = new ArrayList<WiiRemoteDisconnectionListener>();
	private List<CoronataWiiRemoteConnectionObserver> connectionObservers = new ArrayList<CoronataWiiRemoteConnectionObserver>();
	private List<CoronataConnectionStateObserver> connectionStateObservers = new ArrayList<CoronataConnectionStateObserver>();
	private List<CoronataPhaseObserver> phaseStateObservers = new ArrayList<CoronataPhaseObserver>();
	private List<CoronataFullObserver> fullObservers = new ArrayList<CoronataFullObserver>();

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
	}

	@Override
	public void coronataStarted() {
		for (CoronataFullObserver o : fullObservers) {
			o.coronataStarted();
		}
	}

	@Override
	public void libraryLoaded() {
		for (CoronataFullObserver o : fullObservers) {
			o.libraryLoaded();
		}
	}

	@Override
	public void searchStarted() {
		for (CoronataFullObserver o : fullObservers) {
			o.searchStarted();
		}
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		for (CoronataFullObserver o : fullObservers) {
			o.bluetoothDeviceFound(address, deviceClass);
		}
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		for (CoronataFullObserver o : fullObservers) {
			o.deviceRejectedIdentification(address, deviceClass);
		}
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		for (CoronataFullObserver o : fullObservers) {
			o.deviceIdentifiedAsNotWiiRemote(address, deviceClass);
		}
	}

	@Override
	public void wiiRemoteIdentified() {
		for (CoronataFullObserver o : fullObservers) {
			o.wiiRemoteIdentified();
		}
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		for (CoronataWiiRemoteConnectionObserver o : connectionObservers) {
			o.wiiRemoteConnected(wiiRemote);
		}
	}

	@Override
	public void searchFinished() {
		for (CoronataFullObserver o : fullObservers) {
			o.searchFinished();
		}
	}

	@Override
	public void errorOccurred(CoronataException e) {
		for (CoronataFullObserver o : fullObservers) {
			o.errorOccurred(e);
		}
	}

	@Override
	public void enteredIdleState() {
		for (CoronataConnectionStateObserver o : connectionStateObservers) {
			o.enteredIdleState();
		}
	}

	@Override
	public void enteredInProcessState() {
		for (CoronataConnectionStateObserver o : connectionStateObservers) {
			o.enteredInProcessState();
		}
	}

	@Override
	public void enteredConnectedState() {
		for (CoronataConnectionStateObserver o : connectionStateObservers) {
			o.enteredConnectedState();
		}
	}

	@Override
	public void starting() {
		for (CoronataPhaseObserver o : phaseStateObservers) {
			o.starting();
		}
	}

	@Override
	public void running(CoronataPhase coronataPhase) {
		for (CoronataPhaseObserver o : phaseStateObservers) {
			o.running(coronataPhase);
		}
	}

	@Override
	public void success(CoronataPhase coronataPhase) {
		for (CoronataPhaseObserver o : phaseStateObservers) {
			o.success(coronataPhase);
		}
	}

	@Override
	public void failure(CoronataPhase coronataPhase) {
		for (CoronataPhaseObserver o : phaseStateObservers) {
			o.failure(coronataPhase);
		}
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
		connectionStateObservers.add(o);
	}

	public void addPhaseStateObserver(CoronataPhaseObserver o) {
		phaseStateObservers.add(o);
	}

	public void addFullObserver(CoronataFullObserver o) {
		fullObservers.add(o);
	}
	
}
