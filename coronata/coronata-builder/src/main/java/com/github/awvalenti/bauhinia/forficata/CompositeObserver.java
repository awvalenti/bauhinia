package com.github.awvalenti.bauhinia.forficata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;

class CompositeObserver implements ForficataObserver {

	private final List<ForficataObserver> all = new ArrayList<ForficataObserver>();

	public void add(ForficataObserver eventListener) {
		all.add(eventListener);
	}

	@Override
	public void forficataStarted() {
		for (ForficataObserver l : all) {
			l.forficataStarted();
		}
	}

	@Override
	public void librariesLoaded() {
		for (ForficataObserver l : all) {
			l.librariesLoaded();
		}
	}

	@Override
	public void searchStarted() {
		for (ForficataObserver l : all) {
			l.searchStarted();
		}
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		for (ForficataObserver l : all) {
			l.bluetoothDeviceFound(address, deviceClass);
		}
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		for (ForficataObserver l : all) {
			l.deviceRejectedIdentification(address, deviceClass);
		}
	}

	@Override
	public void deviceIdentifiedAsNotWiimote(String address, String deviceClass) {
		for (ForficataObserver l : all) {
			l.deviceIdentifiedAsNotWiimote(address, deviceClass);
		}
	}

	@Override
	public void wiimoteIdentified() {
		for (ForficataObserver l : all) {
			l.wiimoteIdentified();
		}
	}

	@Override
	public void wiimoteConnected(Wiimote wiimote) {
		for (ForficataObserver l : all) {
			l.wiimoteConnected(wiimote);
		}
	}

	@Override
	public void searchFinished() {
		for (ForficataObserver l : all) {
			l.searchFinished();
		}
	}

	@Override
	public void errorOccurred(ForficataException e) {
		for (ForficataObserver l : all) {
			l.errorOccurred(e);
		}
	}

}
