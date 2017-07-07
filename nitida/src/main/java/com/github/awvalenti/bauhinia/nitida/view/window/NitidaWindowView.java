package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.github.awvalenti.bauhinia.nitida.view.common.properties.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.ApplicationStatePanel;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.LogPanel;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.UserInputPanel;

public class NitidaWindowView {

	public NitidaWindowView(ProjectProperties projectProperties,
			ApplicationStatePanel applicationStatePanel,
			LogPanel logPanel, UserInputPanel userInputPanel) {
		JFrame frame = new JFrame("nitida " + projectProperties.getProjectVersion());
		frame.setLayout(new BorderLayout());
		frame.setSize(320, 520);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(applicationStatePanel, BorderLayout.NORTH);
		frame.add(logPanel, BorderLayout.CENTER);
		frame.add(userInputPanel, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

}
