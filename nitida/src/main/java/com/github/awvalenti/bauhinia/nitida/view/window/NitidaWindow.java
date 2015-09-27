package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.forficata.ForficataFailure;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataPhaseObserver;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;

public class NitidaWindow implements ForficataPhaseObserver {

	private final JFrame frame;
	private final ApplicationStatePanel statePanel;
	private final RetryButton retryButton;
	private final LogPanel logPanel;

	public NitidaWindow(ProjectProperties projectProperties, RetryButton retryButton) {
		this.retryButton = retryButton;
		this.statePanel = new ApplicationStatePanel(new PhasePanel(Phase.values()));
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

	public void run() {
		frame.setVisible(true);
	}

//	@Override
//	public void searchStarted() {
//		logPanel.append("Search started");
//		statePanel.stateChanged(Phase.LOAD_LIBRARIES, PhaseState.SUCCESS);
//		statePanel.stateChanged(Phase.FIND_WIIMOTE, PhaseState.RUNNING);
//		retryButton.setEnabled(false);
//	}
//
//	@Override
//	public void identifyingBluetoothDevice(String deviceAddress, String deviceClass) {
//		logPanel.append(String.format("Device found: %s - %s",
//				deviceAddress, deviceClass));
//	}
//
//	@Override
//	public void wiimoteFound() {
//		logPanel.append("Wiimote found. Connecting...");
//		statePanel.stateChanged(Phase.FIND_WIIMOTE, PhaseState.SUCCESS);
//		statePanel.stateChanged(Phase.CONNECT_TO_WIIMOTE, PhaseState.RUNNING);
//	}
//
//	@Override
//	public void remoteControlActivated() {
//		logPanel.append("Connected. Remote control is active!");
//		statePanel.stateChanged(Phase.FIND_WIIMOTE, PhaseState.SUCCESS);
//		statePanel.stateChanged(Phase.CONNECT_TO_WIIMOTE, PhaseState.SUCCESS);
//	}
//
//	@Override
//	public void wiimoteDisconnected() {
//		logPanel.append("Wiimote disconnected");
//		statePanel.stateChanged(Phase.LOAD_LIBRARIES, PhaseState.SUCCESS);
//		retryButton.setEnabled(true);
//	}
//
//	@Override
//	public void unableToFindWiimote() {
//		logPanel.append("Unable to find Wiimote");
//		statePanel.stateChanged(Phase.FIND_WIIMOTE, PhaseState.FAILURE);
//		retryButton.setEnabled(true);
//	}
//
//	@Override
//	public void errorOccurred(ForficataException e) {
//		logPanel.append(String.format("An unexpected exception occurred: %s", e));
//		retryButton.setEnabled(true);
//	}

	@Override
	public void running(com.github.awvalenti.bauhinia.forficata.Phase phase) {
		retryButton.setEnabled(false);
		statePanel.stateChanged(Phase.valueOf(phase.name()), PhaseState.RUNNING);
	}

	@Override
	public void success(com.github.awvalenti.bauhinia.forficata.Phase phase) {
		statePanel.stateChanged(Phase.valueOf(phase.name()), PhaseState.SUCCESS);
	}

	@Override
	public void failure(com.github.awvalenti.bauhinia.forficata.Phase phase,
			ForficataFailure failure) {
		statePanel.stateChanged(Phase.valueOf(phase.name()), PhaseState.FAILURE);
		retryButton.setEnabled(true);
	}

	public void wiimoteDisconnected() {
		statePanel.reset();
		retryButton.setEnabled(true);
	}

}
