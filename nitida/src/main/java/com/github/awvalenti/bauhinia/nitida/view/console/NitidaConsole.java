package com.github.awvalenti.bauhinia.nitida.view.console;

import com.github.awvalenti.bauhinia.nitida.model.NitidaOutput;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;

public class NitidaConsole implements NitidaOutput {

	private final ProjectProperties projectProperties;

	public NitidaConsole(ProjectProperties projectProperties) {
		this.projectProperties = projectProperties;
	}

	@Override
	public void run() {
		System.out.printf("nitida %s\n\n", projectProperties.getProjectVersion());
	}

	@Override
	public void idle() {
		System.out.println("Wiimote disconnected");
	}

	@Override
	public void searching() {
		System.out.println("Searching for Wiimote...");
	}

	@Override
	public void bluetoothDeviceFound(String bluetoothAddress, String deviceClass) {
		System.out.printf("Bluetooth device found: %s - %s\n", bluetoothAddress, deviceClass);
	}

	@Override
	public void identifying() {
		System.out.println("Identifying Bluetooth device...");
	}

	@Override
	public void notWiimote() {
		System.out.println("Not a Wiimote");
	}

	@Override
	public void active() {
		System.out.println("Connected to Wiimote!");
	}

	@Override
	public void unexpectedException(Exception e) {
		e.printStackTrace();
		System.err.println("\n" + e.getMessage());
	}

}
