package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class CurrentStepStatePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final Map<Step, StepIndication> stepsToIndications = new HashMap<Step, StepIndication>();

	public CurrentStepStatePanel(Step[] steps) {
		super(new GridLayout(steps.length, 1));

		for (Step step : steps) {
			StepIndication indication = new StepIndication(step.toString());
			add(indication);
			stepsToIndications.put(step, indication);
		}
	}

	public void stateChanged(Step step, StepState state) {
		stepsToIndications.get(step).setState(state);
	}

}
