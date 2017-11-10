package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.List;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class CompositeLifecycleEventsObserver implements
		CoronataLifecycleEventsObserver {

	private final List<CoronataLifecycleEventsObserver> observers =
			new ArrayList<CoronataLifecycleEventsObserver>();

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
	public void libraryLoadedSearchStarted() {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.libraryLoadedSearchStarted();
		}
	}

	@Override
	public void bluetoothDeviceFound(String btAddress, String deviceClass) {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.bluetoothDeviceFound(btAddress, deviceClass);
		}
	}

	@Override
	public void identificationRejected(String btAddress) {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.identificationRejected(btAddress);
		}
	}

	@Override
	public void identifiedAsNonWiiRemote(String btAddress) {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.identifiedAsNonWiiRemote(btAddress);
		}
	}

	@Override
	public void identifiedAsWiiRemote(String btAddressOrNull) {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.identifiedAsWiiRemote(btAddressOrNull);
		}
	}

	@Override
	public void connectionRejected(String btAddress) {
		for (CoronataLifecycleEventsObserver o : observers) {
			o.connectionRejected(btAddress);
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
