package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ApplicationStatePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final PhasePanel phasePanel;

	public ApplicationStatePanel(PhasePanel phasePanel) {
		super(new BorderLayout());
		this.phasePanel = phasePanel;

		setBorder(BorderFactory.createTitledBorder("Application state"));
		add(phasePanel, BorderLayout.CENTER);
		add(new MacroStatePanel(MacroState.values()), BorderLayout.SOUTH);
	}

	public void stateChanged(Phase phase, PhaseState state) {
		phasePanel.phaseStateChanged(phase, state);
	}

}
