package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class CompositeLifecycleEventsObserver implements CoronataLifecycleEventsObserver {

	private final List<CoronataLifecycleEventsObserver> observers = new ArrayList<CoronataLifecycleEventsObserver>();

	public void add(CoronataLifecycleEventsObserver o) {
		observers.add(o);
	}

	@Override
	public void coronataStarted() {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.coronataStarted();
		}
	}

	@Override
	public void libraryLoaded() {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.libraryLoaded();
		}
	}

	@Override
	public void searchStarted() {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.searchStarted();
		}
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.bluetoothDeviceFound(address, deviceClass);
		}
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.deviceRejectedIdentification(address, deviceClass);
		}
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.deviceIdentifiedAsNotWiiRemote(address, deviceClass);
		}
	}

	@Override
	public void wiiRemoteIdentified() {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.wiiRemoteIdentified();
		}
	}

	@Override
	public void connected(CoronataWiiRemote wiiRemote) {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.connected(wiiRemote);
		}
	}

	@Override
	public void searchFinished() {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.searchFinished();
		}
	}

	@Override
	public void disconnected() {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.disconnected();
		}
	}

	@Override
	public void errorOccurred(CoronataException e) {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.errorOccurred(e);
		}
	}

}
