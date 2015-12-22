package com.github.awvalenti.bauhinia.nitida.view.console;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.WiiRemote;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;

public class NitidaConsole implements CoronataFullObserver {

	private final ProjectProperties projectProperties;

	public NitidaConsole(ProjectProperties projectProperties) {
		this.projectProperties = projectProperties;
	}

	@Override
	public void coronataStarted() {
		System.out.printf("nitida %s\n\n", projectProperties.getProjectVersion());
	}

	@Override
	public void librariesLoaded() {
		System.out.println("Libraries loaded successfuly");
	}

	@Override
	public void searchStarted() {
		System.out.println("Searching for Wii Remote...");
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		System.out.printf("Found a Bluetooth device at %s: %s\n", address, deviceClass);
	}

	@Override
	public void wiiRemoteIdentified() {
		System.out.println("Found Wii Remote. Connecting...");
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		System.out.println("Device at " + address + " rejected identification");
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		System.out.println("Device at " + address + " identified as not Wii Remote");
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		System.out.println("Connected. Remote control is active!");
	}

	@Override
	public void searchFinished() {
		System.out.println("Finished search");
	}

	@Override
	public void errorOccurred(CoronataException e) {
		e.printStackTrace();
		System.err.println("\n" + e.getMessage());
	}

}
