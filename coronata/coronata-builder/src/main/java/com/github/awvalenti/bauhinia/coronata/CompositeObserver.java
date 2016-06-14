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
		for (CoronataFullObserver o : all) {
			o.coronataStarted();
		}
	}

	@Override
	public void librariesLoaded() {
		for (CoronataFullObserver o : all) {
			o.librariesLoaded();
		}
	}

	@Override
	public void searchStarted() {
		for (CoronataFullObserver o : all) {
			o.searchStarted();
		}
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		for (CoronataFullObserver o : all) {
			o.bluetoothDeviceFound(address, deviceClass);
		}
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		for (CoronataFullObserver o : all) {
			o.deviceRejectedIdentification(address, deviceClass);
		}
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		for (CoronataFullObserver o : all) {
			o.deviceIdentifiedAsNotWiiRemote(address, deviceClass);
		}
	}

	@Override
	public void wiiRemoteIdentified() {
		for (CoronataFullObserver o : all) {
			o.wiiRemoteIdentified();
		}
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		for (CoronataFullObserver o : all) {
			o.wiiRemoteConnected(wiiRemote);
		}
	}

	@Override
	public void searchFinished() {
		for (CoronataFullObserver o : all) {
			o.searchFinished();
		}
	}

	@Override
	public void errorOccurred(CoronataException e) {
		for (CoronataFullObserver o : all) {
			o.errorOccurred(e);
		}
	}

}
