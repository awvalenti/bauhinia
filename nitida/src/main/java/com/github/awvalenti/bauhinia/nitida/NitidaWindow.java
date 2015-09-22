package com.github.awvalenti.bauhinia.nitida;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class NitidaWindow {

	private final JFrame frame;

	public NitidaWindow(ProjectProperties projectProperties) {
		frame = new JFrame("nitida " + projectProperties.getProjectVersion());
		frame.setLayout(new BorderLayout());
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String[] states = { "Idle", "Connecting", "Active" };

		JPanel state = new JPanel();
		state.setBorder(new TitledBorder("State"));
		state.setLayout(new GridLayout(states.length, 1));
		for (String s : states) {
			state.add(new JLabel(s));
		}
		frame.add(state, BorderLayout.NORTH);

		JPanel actions = new JPanel();
		actions.setBorder(new TitledBorder("Actions"));
		actions.add(new JButton("Reconnect"));
		frame.add(actions, BorderLayout.SOUTH);

		JPanel log = new JPanel(new BorderLayout());
		log.setBorder(new TitledBorder("Log"));
		frame.add(log, BorderLayout.CENTER);
	}

	public void run() {
		frame.setVisible(true);
	}

}
