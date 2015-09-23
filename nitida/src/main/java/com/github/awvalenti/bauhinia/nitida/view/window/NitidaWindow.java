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

	public NitidaWindow(ProjectProperties projectProperties) {
		statePanel = new StatePanel();

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

	@Override
	public void run() {
		frame.setVisible(true);
	}

	@Override
	public void idle() {
		statePanel.setIdleState();
		connectButton.setEnabled(true);
	}

	@Override
	public void searching() {
		statePanel.setSearchingState();
		connectButton.setEnabled(false);
	}

	@Override
	public void active() {
		statePanel.setActiveState();
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

	private void appendToLog(String content) {
		Document doc = logText.getDocument();
		try {
			doc.insertString(doc.getLength(), content, null);
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void identifying() {
	}

	@Override
	public void notWiimote() {
	}

}
