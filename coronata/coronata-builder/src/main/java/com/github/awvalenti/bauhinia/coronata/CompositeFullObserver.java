package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;

class CompositeFullObserver implements CoronataFullObserver {

	private final List<CoronataFullObserver> observers = new ArrayList<CoronataFullObserver>();

	public void add(CoronataFullObserver o) {
		observers.add(o);
	}

	@Override
	public void coronataStarted() {
		for (CoronataFullObserver o : observers) {
			o.coronataStarted();
		}
	}

	@Override
	public void libraryLoaded() {
		for (CoronataFullObserver o : observers) {
			o.libraryLoaded();
		}
	}

	@Override
	public void searchStarted() {
		for (CoronataFullObserver o : observers) {
			o.searchStarted();
		}
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		for (CoronataFullObserver o : observers) {
			o.bluetoothDeviceFound(address, deviceClass);
		}
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		for (CoronataFullObserver o : observers) {
			o.deviceRejectedIdentification(address, deviceClass);
		}
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		for (CoronataFullObserver o : observers) {
			o.deviceIdentifiedAsNotWiiRemote(address, deviceClass);
		}
	}

	@Override
	public void wiiRemoteIdentified() {
		for (CoronataFullObserver o : observers) {
			o.wiiRemoteIdentified();
		}
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		for (CoronataFullObserver o : observers) {
			o.wiiRemoteConnected(wiiRemote);
		}
	}

	@Override
	public void searchFinished() {
		for (CoronataFullObserver o : observers) {
			o.searchFinished();
		}
	}

	@Override
	public void wiiRemoteDisconnected() {
		for (CoronataFullObserver o : observers) {
			o.wiiRemoteDisconnected();
		}
	}

	@Override
	public void errorOccurred(CoronataException e) {
		for (CoronataFullObserver o : observers) {
			o.errorOccurred(e);
		}
	}

}
