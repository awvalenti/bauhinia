package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.coronata.CoronataPhase;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;

public class PhasesPanel extends JPanel implements CoronataPhaseObserver {

	private static final long serialVersionUID = 1L;

	private final Map<CoronataPhase, PhaseWidget> map = new HashMap<CoronataPhase, PhaseWidget>();

	public PhasesPanel() {
		super(new GridLayout(CoronataPhase.values().length, 1));

		for (CoronataPhase coronataPhase : CoronataPhase.values()) {
			PhaseWidget indication = new PhaseWidget(coronataPhase.toString());
			map.put(coronataPhase, indication);
			add(indication);
		}
	}

	@Override
	public void reset() {
		for (PhaseWidget indication : map.values()) {
			indication.setState(PhaseState.INACTIVE);
		}
	}

	@Override
	public void running(CoronataPhase coronataPhase) {
		map.get(coronataPhase).setState(PhaseState.RUNNING);
	}

	@Override
	public void success(CoronataPhase coronataPhase) {
		map.get(coronataPhase).setState(PhaseState.SUCCESS);
	}

	@Override
	public void failure(CoronataPhase coronataPhase) {
		map.get(coronataPhase).setState(PhaseState.FAILURE);
	}

}
