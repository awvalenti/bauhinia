package com.github.awvalenti.bauhinia.nitida.view.window;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ActionPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ActionPanel(RetryButton retryButton) {
		setBorder(BorderFactory.createTitledBorder("Actions"));
		add(retryButton);
	}

}
