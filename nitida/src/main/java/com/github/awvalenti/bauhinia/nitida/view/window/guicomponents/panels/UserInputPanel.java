package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class UserInputPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public UserInputPanel(
			PresentationAppPanel presentationAppPanel,
			ActionPanel actionPanel, HelpPanel helpPanel) {
		super(new GridLayout(3, 1));
		add(presentationAppPanel);
		add(actionPanel);
		add(helpPanel);
	}

}
