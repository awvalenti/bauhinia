package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.Wiimote;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataObserver;
import com.github.awvalenti.bauhinia.nitida.view.window.InformationPane.HorizontalScrolling;

public class LogPanel extends JPanel implements CoronataObserver {

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
	public void librariesLoaded() {
	}

	@Override
	public void searchStarted() {
		append("Searching for Wiimote...");
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
	public void deviceIdentifiedAsNotWiimote(String address, String deviceClass) {
		append("Device at " + address + " identified as not Wiimote");
	}

	@Override
	public void wiimoteIdentified() {
		append("Found Wiimote. Connecting...");
	}

	@Override
	public void wiimoteConnected(Wiimote wiimote) {
		append("Connected. Remote control is active!");
	}

	@Override
	public void searchFinished() {
		append("Finished search");
	}

	@Override
	public void errorOccurred(CoronataException e) {
		append(e.getMessage());
	}

}
