package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;

public class NitidaWindow {

	private final JFrame frame;

	public NitidaWindow(ProjectProperties projectProperties) {
		frame = new JFrame("nitida " + projectProperties.getProjectVersion());
		frame.setLayout(new BorderLayout());
		frame.setSize(320, 480);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new ApplicationStatePanel(new PhasePanel(Phase.values())), BorderLayout.NORTH);
		frame.add(new LogPanel(), BorderLayout.CENTER);
		frame.add(new ActionPanel(new RetryButton()), BorderLayout.SOUTH);
	}

	public void run() {
		frame.setVisible(true);
	}

}
