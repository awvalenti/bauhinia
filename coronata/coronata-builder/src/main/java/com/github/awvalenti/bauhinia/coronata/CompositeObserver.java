package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.WiiRemote;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;

class CompositeObserver implements CoronataFullObserver {

	private final List<CoronataFullObserver> all = new ArrayList<CoronataFullObserver>();

	public void add(CoronataFullObserver eventListener) {
		all.add(eventListener);
	}

	@Override
	public void coronataStarted() {
		for (CoronataFullObserver l : all) {
			l.coronataStarted();
		}
	}

	@Override
	public void librariesLoaded() {
		for (CoronataFullObserver l : all) {
			l.librariesLoaded();
		}
	}

	@Override
	public void searchStarted() {
		for (CoronataFullObserver l : all) {
			l.searchStarted();
		}
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		for (CoronataFullObserver l : all) {
			l.bluetoothDeviceFound(address, deviceClass);
		}
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		for (CoronataFullObserver l : all) {
			l.deviceRejectedIdentification(address, deviceClass);
		}
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		for (CoronataFullObserver l : all) {
			l.deviceIdentifiedAsNotWiiRemote(address, deviceClass);
		}
	}

	@Override
	public void wiiRemoteIdentified() {
		for (CoronataFullObserver l : all) {
			l.wiiRemoteIdentified();
		}
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		for (CoronataFullObserver l : all) {
			l.wiiRemoteConnected(wiiRemote);
		}
	}

	@Override
	public void searchFinished() {
		for (CoronataFullObserver l : all) {
			l.searchFinished();
		}
	}

	@Override
	public void errorOccurred(CoronataException e) {
		for (CoronataFullObserver l : all) {
			l.errorOccurred(e);
		}
	}

}
