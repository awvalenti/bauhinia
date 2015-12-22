package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class UserInputPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public UserInputPanel(ActionPanel actionPanel, HelpPanel helpPanel) {
		super(new GridLayout(2, 1));
		add(actionPanel);
		add(helpPanel);
	}

}
