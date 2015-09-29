package com.github.awvalenti.bauhinia.nitida.view.console;

import com.github.awvalenti.bauhinia.forficata.ForficataException;
import com.github.awvalenti.bauhinia.forficata.Wiimote;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;

public class NitidaConsole implements ForficataObserver {

	private final ProjectProperties projectProperties;

	public NitidaConsole(ProjectProperties projectProperties) {
		this.projectProperties = projectProperties;
	}

	@Override
	public void forficataStarted() {
		System.out.printf("nitida %s\n\n", projectProperties.getProjectVersion());
	}

	@Override
	public void librariesLoaded() {
		System.out.println("Libraries loaded successfuly");
	}

	@Override
	public void searchStarted() {
		System.out.println("Searching for Wiimote...");
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		System.out.printf("Found a Bluetooth device at %s: %s\n", address, deviceClass);
	}

	@Override
	public void wiimoteIdentified() {
		System.out.println("Found Wiimote. Connecting...");
	}

	@Override
	public void wiimoteConnected(Wiimote wiimote) {
		System.out.println("Connected. Remote control is active!");
	}

	@Override
	public void searchFinished() {
		System.out.println("Finished search");
	}

	@Override
	public void errorOccurred(ForficataException e) {
		e.printStackTrace();
		System.err.println("\n" + e.getMessage());
	}

}
