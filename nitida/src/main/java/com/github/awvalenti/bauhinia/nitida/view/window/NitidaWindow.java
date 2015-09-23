package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.github.awvalenti.bauhinia.nitida.model.NitidaOutput;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;

public class NitidaWindow implements NitidaOutput {

	private final JFrame frame;
	private final StatePanel statePanel;
	private final JButton connectButton;
	private final JEditorPane logText;

	public NitidaWindow(ProjectProperties projectProperties, StatePanel statePanel) {
		this.statePanel = statePanel;

		frame = new JFrame("nitida " + projectProperties.getProjectVersion());
		frame.setLayout(new BorderLayout());
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(statePanel, BorderLayout.NORTH);

		logText = new JEditorPane();
		logText.setEditable(false);

		JPanel logPanel = new JPanel(new BorderLayout());
		logPanel.setBorder(BorderFactory.createTitledBorder("Log"));
		logPanel.add(logText);
		frame.add(logPanel, BorderLayout.CENTER);

		connectButton = new JButton("Connect");
		connectButton.setEnabled(false);

		JPanel actions = new JPanel();
		actions.setBorder(BorderFactory.createTitledBorder("Actions"));
		actions.add(connectButton);
		frame.add(actions, BorderLayout.SOUTH);
	}

	private void appendToLog(String content) {
		Document doc = logText.getDocument();
		try {
			doc.insertString(doc.getLength(), content, null);
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
	}

	public void show() {
		frame.setVisible(true);
	}

	@Override
	public void enteredIdleState() {
		statePanel.enteredIdleState();
		connectButton.setEnabled(true);
	}

	@Override
	public void enteredSearchingStarted() {
		statePanel.enteredSearchingStarted();
		connectButton.setEnabled(false);
	}

	@Override
	public void enteredActiveState() {
		statePanel.enteredActiveState();
		connectButton.setEnabled(false);
	}

	@Override
	public void bluetoothDeviceFound(String bluetoothAddress, String deviceClass) {
		appendToLog(String.format("Device found at %s: %s\n", bluetoothAddress, deviceClass));
	}

	@Override
	public void unexpectedException(Exception e) {
		appendToLog(String.format("An unexpected exception occurred: %s", e));
	}

}
