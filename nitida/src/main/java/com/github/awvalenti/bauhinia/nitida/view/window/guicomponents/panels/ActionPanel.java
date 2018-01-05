package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction.StartButton;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction.StopButton;

public class ActionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ActionPanel(StartButton startButton, StopButton stopButton) {
		setBorder(BorderFactory.createTitledBorder("Actions"));
		add(startButton);
		add(stopButton);
	}

}
