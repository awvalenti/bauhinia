package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.WiiRemote;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;
import com.github.awvalenti.bauhinia.nitida.view.window.InformationPane.HorizontalScrolling;

// TODO Merge similar features with NitidaConsole
public class LogPanel extends JPanel implements CoronataFullObserver {

	private static final long serialVersionUID = 1L;

	private final InformationPane informationPane = new InformationPane(HorizontalScrolling.NEVER);

	public LogPanel() {
		super(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Log"));
		add(informationPane);
	}

	private synchronized void append(String content) {
		informationPane.append(content);
	}

	@Override
	public void coronataStarted() {
	}

	@Override
	public void libraryLoaded() {
		append("Library loaded successfuly");
	}

	@Override
	public void searchStarted() {
		append("Searching for Wii Remote...");
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		append(String.format("Bluetooth device found at %s: %s", address, deviceClass));
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		append("Device at " + address + " rejected identification");
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		append("Device at " + address + " identified as not Wii Remote");
	}

	@Override
	public void wiiRemoteIdentified() {
		append("Found Wii Remote. Connecting...");
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		append("Connected. Remote control is active!");
	}

	@Override
	public void searchFinished() {
		append("Finished search");
	}

	@Override
	public void wiiRemoteDisconnected() {
		append("Wii Remote disconnected");
	}

	@Override
	public void errorOccurred(CoronataException e) {
		append(e.getMessage());
	}

}
