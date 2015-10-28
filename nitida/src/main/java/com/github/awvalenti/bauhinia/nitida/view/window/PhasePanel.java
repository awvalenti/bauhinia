package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.forficata.CoronataFailure;
import com.github.awvalenti.bauhinia.forficata.Phase;
import com.github.awvalenti.bauhinia.forficata.observers.CoronataPhaseObserver;

public class PhasePanel extends JPanel implements CoronataPhaseObserver {

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

	@Override
	public void starting() {
		for (PhaseIndication indication : map.values()) {
			indication.setState(PhaseState.INACTIVE);
		}
	}

	@Override
	public void running(Phase phase) {
		map.get(phase).setState(PhaseState.RUNNING);
	}

	@Override
	public void success(Phase phase) {
		map.get(phase).setState(PhaseState.SUCCESS);
	}

	@Override
	public void failure(Phase phase, CoronataFailure failure) {
		map.get(phase).setState(PhaseState.FAILURE);
	}

}
