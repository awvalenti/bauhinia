package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.coronata.CoronataPhase;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;

public class PhasePanel extends JPanel implements CoronataPhaseObserver {

	private static final long serialVersionUID = 1L;

	private final Map<CoronataPhase, PhaseIndication> map = new HashMap<CoronataPhase, PhaseIndication>();

	public PhasePanel() {
		super(new GridLayout(CoronataPhase.values().length, 1));

		for (CoronataPhase coronataPhase : CoronataPhase.values()) {
			PhaseIndication indication = new PhaseIndication(coronataPhase.toString());
			map.put(coronataPhase, indication);
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
