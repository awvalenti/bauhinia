package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction.RetryButton;

public class ActionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ActionPanel(RetryButton retryButton) {
		setBorder(BorderFactory.createTitledBorder("Actions"));
		add(retryButton);
	}

}
