package com.github.awvalenti.bauhinia.nitida.model;

import com.github.awvalenti.bauhinia.forficata.ForficataException;

public interface NitidaOutputListener {

	void run();

	void searchStarted();

	void identifyingBluetoothDevice(String deviceAddress, String deviceClass);

	void wiimoteFound();

	void remoteControlActivated();

	void wiimoteDisconnected();

	void unableToFindWiimote();

	void errorOccurred(ForficataException e);

}
