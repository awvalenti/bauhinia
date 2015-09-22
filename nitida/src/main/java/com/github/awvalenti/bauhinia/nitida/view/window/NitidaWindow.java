package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;

public class NitidaWindow {

	private final JFrame frame;

	public NitidaWindow(ProjectProperties projectProperties) {
		frame = new JFrame("nitida " + projectProperties.getProjectVersion());
		frame.setLayout(new BorderLayout());
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		StateLabel[] stateLabels = { new StateLabel("Idle", Color.RED),
				new StateLabel("Connecting", Color.YELLOW), new StateLabel("Active", Color.GREEN) };

		JPanel pnlState = new JPanel();
		pnlState.setBorder(BorderFactory.createTitledBorder("State"));
		pnlState.setLayout(new GridLayout(stateLabels.length, 1));
		for (StateLabel sl : stateLabels) {
			pnlState.add(sl);
			sl.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					((StateLabel) e.getSource()).setEnabled(!((StateLabel) e.getSource()).isEnabled());
				}
			});
		}
		frame.add(pnlState, BorderLayout.NORTH);

		JPanel log = new JPanel(new BorderLayout());
		log.setBorder(BorderFactory.createTitledBorder("Log"));
		frame.add(log, BorderLayout.CENTER);

		JButton reconnect = new JButton("Reconnect");
		reconnect.setEnabled(false);

		JPanel actions = new JPanel();
		actions.setBorder(BorderFactory.createTitledBorder("Actions"));
		actions.add(reconnect);
		frame.add(actions, BorderLayout.SOUTH);
	}

	public void run() {
		frame.setVisible(true);
	}

}
