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
	private final ConnectButton connectButton;
	private final LogPanel logPanel;

	public NitidaWindow(ProjectProperties projectProperties, ConnectButton connectButton) {
		this.connectButton = connectButton;
		this.statePanel = new StatePanel();
		this.logPanel = new LogPanel();

		frame = new JFrame("nitida " + projectProperties.getProjectVersion());
		frame.setLayout(new BorderLayout());
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(statePanel, BorderLayout.NORTH);

		frame.add(logPanel, BorderLayout.CENTER);

		JPanel actions = new JPanel();
		actions.setBorder(BorderFactory.createTitledBorder("Actions"));
		actions.add(connectButton);
		frame.add(actions, BorderLayout.SOUTH);
	}

	@Override
	public void run() {
		frame.setVisible(true);
	}

	@Override
	public void searchStarted() {
		statePanel.setSearchingState();
		connectButton.setEnabled(false);
	}

	@Override
	public void identifyingBluetoothDevice(String deviceAddress, String deviceClass) {
		logPanel.appendToLog(String.format("Device found: %s - %s (TODO: add a state label)",
				deviceAddress, deviceClass));
	}

	@Override
	public void wiimoteFound() {
		logPanel.appendToLog("Wiimote found. Connecting... (TODO: add a state label)");
	}

	@Override
	public void robotActivated() {
		logPanel.appendToLog("Connected. Robot activated!");
		statePanel.setActiveState();
		connectButton.setEnabled(false);
	}

	@Override
	public void wiimoteDisconnected() {
		logPanel.appendToLog("Wiimote disconnected");
		statePanel.setIdleState();
		connectButton.setEnabled(true);
	}

	@Override
	public void unableToFindWiimote() {
		logPanel.appendToLog("Unable to find Wiimote");
		statePanel.setIdleState();
		connectButton.setEnabled(true);
	}

	@Override
	public void errorOccurred(ForficataException e) {
		logPanel.appendToLog(String.format("An unexpected exception occurred: %s", e));
	}

}
