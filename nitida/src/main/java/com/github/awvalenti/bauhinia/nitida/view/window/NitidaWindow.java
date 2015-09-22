package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.forficata.api.ForficataAsyncListener;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;

public class NitidaWindow implements ForficataAsyncListener {

	private final JFrame frame;
	private final StatePanel statePanel;
	private final JButton btnConnect;

	public NitidaWindow(ProjectProperties projectProperties, StatePanel statePanel) {
		this.statePanel = statePanel;

		frame = new JFrame("nitida " + projectProperties.getProjectVersion());
		frame.setLayout(new BorderLayout());
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(statePanel, BorderLayout.NORTH);

		JPanel log = new JPanel(new BorderLayout());
		log.setBorder(BorderFactory.createTitledBorder("Log"));
		frame.add(log, BorderLayout.CENTER);

		btnConnect = new JButton("Connect");
		btnConnect.setEnabled(false);

		JPanel actions = new JPanel();
		actions.setBorder(BorderFactory.createTitledBorder("Actions"));
		actions.add(btnConnect);
		frame.add(actions, BorderLayout.SOUTH);
	}

	public void show() {
		frame.setVisible(true);
	}

	@Override
	public void setIdleState() {
		statePanel.setIdleState();
		btnConnect.setEnabled(true);
	}

	@Override
	public void setSearchingState() {
		statePanel.setSearchingState();
		btnConnect.setEnabled(false);
	}

	@Override
	public void setConnectedState() {
		statePanel.setConnectedState();
		btnConnect.setEnabled(false);
	}

}
