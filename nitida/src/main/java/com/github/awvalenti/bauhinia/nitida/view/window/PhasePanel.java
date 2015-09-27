package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class PhasePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final Map<Phase, PhaseIndication> map = new HashMap<Phase, PhaseIndication>();

	public PhasePanel(Phase[] phases) {
		super(new GridLayout(phases.length, 1));
		for (Phase phase : phases) {
			PhaseIndication indication = new PhaseIndication(phase.toString());
			map.put(phase, indication);
			add(indication);
		}
	}

	public void phaseStateChanged(Phase phase, PhaseState newState) {
		map.get(phase).setState(newState);
	}

	public void clear() {
		for (PhaseIndication indication : map.values()) {
			indication.setState(PhaseState.INACTIVE);
		}
	}

}
