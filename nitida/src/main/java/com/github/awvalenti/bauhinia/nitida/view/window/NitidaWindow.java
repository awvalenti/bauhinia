package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.forficata.ForficataException;
import com.github.awvalenti.bauhinia.nitida.model.NitidaOutput;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;

public class NitidaWindow implements NitidaOutput {

	private final JFrame frame;
	private final StatePanel statePanel;
	private final RetryButton retryButton;
	private final LogPanel logPanel;

	public NitidaWindow(ProjectProperties projectProperties, RetryButton retryButton) {
		this.retryButton = retryButton;
		this.statePanel = new StatePanel();
		this.logPanel = new LogPanel();

		frame = new JFrame("nitida " + projectProperties.getProjectVersion());
		frame.setLayout(new BorderLayout());
		frame.setSize(320, 480);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(statePanel, BorderLayout.NORTH);

		frame.add(logPanel, BorderLayout.CENTER);

		JPanel actions = new JPanel();
		actions.setBorder(BorderFactory.createTitledBorder("Actions"));
		actions.add(retryButton);
		frame.add(actions, BorderLayout.SOUTH);
	}

	@Override
	public void run() {
		frame.setVisible(true);
	}

	@Override
	public void searchStarted() {
		logPanel.append("Search started");
		statePanel.setSearchingState();
		retryButton.setEnabled(false);
	}

	@Override
	public void identifyingBluetoothDevice(String deviceAddress, String deviceClass) {
		logPanel.append(String.format("Device found: %s - %s",
				deviceAddress, deviceClass));
	}

	@Override
	public void wiimoteFound() {
		logPanel.append("Wiimote found. Connecting...");
		statePanel.setConnectingState();
	}

	@Override
	public void remoteControlActivated() {
		logPanel.append("Connected. Remote control is active!");
		statePanel.setActiveState();
	}

	@Override
	public void wiimoteDisconnected() {
		logPanel.append("Wiimote disconnected");
		statePanel.setIdleState();
		retryButton.setEnabled(true);
	}

	@Override
	public void unableToFindWiimote() {
		logPanel.append("Unable to find Wiimote");
		statePanel.setIdleState();
		retryButton.setEnabled(true);
	}

	@Override
	public void errorOccurred(ForficataException e) {
		logPanel.append(String.format("An unexpected exception occurred: %s", e));
		retryButton.setEnabled(true);
	}

}
