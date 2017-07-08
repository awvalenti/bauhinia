package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import com.github.awvalenti.bauhinia.nitida.model.PresentationApp;

public class PresentationAppComboBox extends JComboBox<PresentationApp> {

	private static final long serialVersionUID = 1L;

	public PresentationAppComboBox() {
		super(PresentationApp.values());

		setSelectedItem(PresentationApp.getDefault());

		((JLabel) getRenderer()).setHorizontalAlignment(JLabel.CENTER);
	}

}
