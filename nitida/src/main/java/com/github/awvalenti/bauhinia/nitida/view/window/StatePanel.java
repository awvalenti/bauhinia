package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

class StatePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final StateLabel
			idle = new StateLabel("Idle", Color.RED),
			connecting = new StateLabel("Connecting", Color.YELLOW),
			active = new StateLabel("Active", Color.GREEN);

	public StatePanel() {
		setBorder(BorderFactory.createTitledBorder("State"));
		setLayout(new GridLayout(3, 1));

		add(idle);
		add(connecting);
		add(active);
	}

}
