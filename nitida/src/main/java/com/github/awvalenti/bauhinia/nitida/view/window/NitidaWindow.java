package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

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

		frame.add(new StatePanel(), BorderLayout.NORTH);

		JPanel log = new JPanel(new BorderLayout());
		log.setBorder(BorderFactory.createTitledBorder("Log"));
		frame.add(log, BorderLayout.CENTER);

		JButton connect = new JButton("Connect");
		connect.setEnabled(false);

		JPanel actions = new JPanel();
		actions.setBorder(BorderFactory.createTitledBorder("Actions"));
		actions.add(connect);
		frame.add(actions, BorderLayout.SOUTH);
	}

	public void run() {
		frame.setVisible(true);
	}

}
