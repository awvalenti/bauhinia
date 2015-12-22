package com.github.awvalenti.bauhinia.nitida;

import java.awt.GridLayout;

import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.nitida.view.window.ActionPanel;
import com.github.awvalenti.bauhinia.nitida.view.window.HelpPanel;

public class UserInputPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public UserInputPanel(ActionPanel actionPanel, HelpPanel helpPanel) {
		super(new GridLayout(2, 1));
		add(actionPanel);
		add(helpPanel);
	}

}
