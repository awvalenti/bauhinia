package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ApplicationStatePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public ApplicationStatePanel(PhasePanel phasePanel, MacroStatePanel macroStatePanel) {
		super(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Application state"));

		add(phasePanel, BorderLayout.CENTER);
		add(macroStatePanel, BorderLayout.SOUTH);
	}

}
