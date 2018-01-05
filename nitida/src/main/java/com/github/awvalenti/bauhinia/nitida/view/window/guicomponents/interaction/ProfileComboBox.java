package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.github.awvalenti.bauhinia.nitida.model.Profile;

public class ProfileComboBox extends JComboBox<Profile> {

	private static final long serialVersionUID = 1L;

	public ProfileComboBox() {
		super(Profile.values());

		setSelectedItem(Profile.getDefault());

		((JLabel) getRenderer()).setHorizontalAlignment(JLabel.CENTER);
	}

}
