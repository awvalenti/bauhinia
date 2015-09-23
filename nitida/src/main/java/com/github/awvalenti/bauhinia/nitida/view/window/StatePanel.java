package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.nitida.model.NitidaOutput;

public class StatePanel extends JPanel implements NitidaOutput {

	private static final long serialVersionUID = 1L;

	private final StateLabel[] labels = {
			new StateLabel("Idle", Color.RED),
			new StateLabel("Connecting", Color.YELLOW),
			new StateLabel("Active", Color.GREEN),
			};

	public StatePanel() {
		setBorder(BorderFactory.createTitledBorder("State"));
		setLayout(new GridLayout(3, 1));

		for (StateLabel sl : labels) {
			add(sl);
		}
	}

	private void setEnabledLabel(int enabledLabelIndex) {
		for (StateLabel sl : labels) {
			sl.setEnabled(false);
		}
		labels[enabledLabelIndex].setEnabled(true);
	}

	@Override
	public void enteredIdleState() {
		setEnabledLabel(0);
	}

	@Override
	public void enteredSearchingStarted() {
		setEnabledLabel(1);
	}

	@Override
	public void enteredActiveState() {
		setEnabledLabel(2);
	}

	@Override
	public void bluetoothDeviceFound(String bluetoothAddress, String deviceClass) {
	}

	@Override
	public void unexpectedException(Exception e) {
	}

}
