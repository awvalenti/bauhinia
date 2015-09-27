package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ApplicationStatePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final PhasePanel phasePanel;
	private final MacroStatePanel macroStatePanel;

	public ApplicationStatePanel(PhasePanel phasePanel) {
		super(new BorderLayout());

		this.phasePanel = phasePanel;
		this.macroStatePanel = new MacroStatePanel(MacroState.values());

		setBorder(BorderFactory.createTitledBorder("Application state"));
		add(phasePanel, BorderLayout.CENTER);
		add(macroStatePanel, BorderLayout.SOUTH);
	}

	public void stateChanged(Phase phase, PhaseState state) {
		phasePanel.phaseStateChanged(phase, state);

		switch (state) {
		case INACTIVE:
		case FAILURE:
			macroStatePanel.setState(MacroState.IDLE);
			break;
		case RUNNING:
			macroStatePanel.setState(MacroState.PREPARING);
			break;
		case SUCCESS:
			macroStatePanel.setState(phase == Phase.CONNECT_TO_WIIMOTE ? MacroState.ACTIVE
					: MacroState.PREPARING);
			break;
		}
	}

	public void reset() {
		phasePanel.clear();
		macroStatePanel.setState(MacroState.IDLE);
	}

}
