package com.github.awvalenti.bauhinia.nitida.model;

import com.github.awvalenti.bauhinia.forficata.ForficataException;

public interface NitidaOutput {

	void run();

	void searchStarted();

	void identifyingBluetoothDevice(String deviceAddress, String deviceClass);

	void wiimoteFound();

	void robotActivated();

	void unableToFindWiimote();

	void errorOccurred(ForficataException e);

}
