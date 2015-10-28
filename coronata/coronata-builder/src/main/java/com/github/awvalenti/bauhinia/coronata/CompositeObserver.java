package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.Wiimote;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataObserver;

class CompositeObserver implements CoronataObserver {

	private final List<CoronataObserver> all = new ArrayList<CoronataObserver>();

	public void add(CoronataObserver eventListener) {
		all.add(eventListener);
	}

	@Override
	public void coronataStarted() {
		for (CoronataObserver l : all) {
			l.coronataStarted();
		}
	}

	@Override
	public void librariesLoaded() {
		for (CoronataObserver l : all) {
			l.librariesLoaded();
		}
	}

	@Override
	public void searchStarted() {
		for (CoronataObserver l : all) {
			l.searchStarted();
		}
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		for (CoronataObserver l : all) {
			l.bluetoothDeviceFound(address, deviceClass);
		}
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		for (CoronataObserver l : all) {
			l.deviceRejectedIdentification(address, deviceClass);
		}
	}

	@Override
	public void deviceIdentifiedAsNotWiimote(String address, String deviceClass) {
		for (CoronataObserver l : all) {
			l.deviceIdentifiedAsNotWiimote(address, deviceClass);
		}
	}

	@Override
	public void wiimoteIdentified() {
		for (CoronataObserver l : all) {
			l.wiimoteIdentified();
		}
	}

	@Override
	public void wiimoteConnected(Wiimote wiimote) {
		for (CoronataObserver l : all) {
			l.wiimoteConnected(wiimote);
		}
	}

	@Override
	public void searchFinished() {
		for (CoronataObserver l : all) {
			l.searchFinished();
		}
	}

	@Override
	public void errorOccurred(CoronataException e) {
		for (CoronataObserver l : all) {
			l.errorOccurred(e);
		}
	}

}
