package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class CurrentStepStatePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final Map<Phase, PhaseIndication> stepsToIndications = new HashMap<Phase, PhaseIndication>();

	public CurrentStepStatePanel(Phase[] steps) {
		super(new GridLayout(steps.length, 1));

		for (Phase phase : steps) {
			PhaseIndication indication = new PhaseIndication(phase.toString());
			add(indication);
			stepsToIndications.put(phase, indication);
		}
	}

	public void stateChanged(Phase phase, PhaseState state) {
		stepsToIndications.get(phase).setState(state);
	}

}
