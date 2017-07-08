package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction.PresentationAppComboBox;

public class PresentationAppPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public PresentationAppPanel(PresentationAppComboBox comboBox) {
		setBorder(BorderFactory.createTitledBorder("Presentation application"));
		add(comboBox);
	}

}
