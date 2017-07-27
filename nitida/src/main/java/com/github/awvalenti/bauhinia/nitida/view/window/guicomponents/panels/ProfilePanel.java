package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction.ProfileComboBox;

public class ProfilePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ProfilePanel(ProfileComboBox comboBox) {
		setBorder(BorderFactory.createTitledBorder("Profile"));
		add(comboBox);
	}

}
