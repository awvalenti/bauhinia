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
	private final ApplicationStatePanel applicationStatePanel;
	private final RetryButton retryButton;
	private final LogPanel logPanel;

	public NitidaWindow(ProjectProperties projectProperties, RetryButton retryButton) {
		this.retryButton = retryButton;
		this.applicationStatePanel = new ApplicationStatePanel(new CurrentStepStatePanel(Step.values()));
		this.logPanel = new LogPanel();

		frame = new JFrame("nitida " + projectProperties.getProjectVersion());
		frame.setLayout(new BorderLayout());
		frame.setSize(320, 480);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(applicationStatePanel, BorderLayout.NORTH);

		frame.add(logPanel, BorderLayout.CENTER);

		JPanel actions = new JPanel();
		actions.setBorder(BorderFactory.createTitledBorder("Actions"));
		actions.add(retryButton);
		frame.add(actions, BorderLayout.SOUTH);
	}

	@Override
	public void run() {
		frame.setVisible(true);
		applicationStatePanel.stateChanged(Step.LOAD_LIBRARIES, StepState.RUNNING);
	}

	@Override
	public void searchStarted() {
		logPanel.append("Search started");
		applicationStatePanel.stateChanged(Step.LOAD_LIBRARIES, StepState.SUCCESS);
		applicationStatePanel.stateChanged(Step.FIND_WIIMOTE, StepState.RUNNING);
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
		applicationStatePanel.stateChanged(Step.FIND_WIIMOTE, StepState.SUCCESS);
		applicationStatePanel.stateChanged(Step.CONNECT_TO_WIIMOTE, StepState.RUNNING);
	}

	@Override
	public void remoteControlActivated() {
		logPanel.append("Connected. Remote control is active!");
		applicationStatePanel.stateChanged(Step.FIND_WIIMOTE, StepState.SUCCESS);
		applicationStatePanel.stateChanged(Step.CONNECT_TO_WIIMOTE, StepState.SUCCESS);
	}

	@Override
	public void wiimoteDisconnected() {
		logPanel.append("Wiimote disconnected");
		applicationStatePanel.stateChanged(Step.LOAD_LIBRARIES, StepState.SUCCESS);
		retryButton.setEnabled(true);
	}

	@Override
	public void unableToFindWiimote() {
		logPanel.append("Unable to find Wiimote");
		applicationStatePanel.stateChanged(Step.FIND_WIIMOTE, StepState.FAILURE);
		retryButton.setEnabled(true);
	}

	@Override
	public void errorOccurred(ForficataException e) {
		logPanel.append(String.format("An unexpected exception occurred: %s", e));
		retryButton.setEnabled(true);
	}

}
