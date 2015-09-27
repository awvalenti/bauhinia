package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class ApplicationStatePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private final CurrentStepStatePanel currentStepStatePanel;

	public ApplicationStatePanel(CurrentStepStatePanel currentStepStatePanel) {
		super(new BorderLayout());
		this.currentStepStatePanel = currentStepStatePanel;

		setBorder(BorderFactory.createTitledBorder("Application state"));
		add(currentStepStatePanel, BorderLayout.CENTER);
		add(new MacroStatePanel(), BorderLayout.SOUTH);
	}

	public void stateChanged(Step step, StepState state) {
		currentStepStatePanel.stateChanged(step, state);
	}

}
