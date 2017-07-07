package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleStateObserver;

public class LifecycleStatePanel extends JPanel implements CoronataLifecycleStateObserver {

	private static final long serialVersionUID = 1L;

	private final ConnectionStateIndication[] indications;

	public LifecycleStatePanel() {
		super(new GridLayout(1, 3));

		indications = new ConnectionStateIndication[] {
				new ConnectionStateIndication("Idle", Color.RED),
				new ConnectionStateIndication("In process", Color.YELLOW),
				new ConnectionStateIndication("Connected", Color.GREEN),
		};

		for (ConnectionStateIndication i : indications) {
			add(i);
		}
	}

	@Override
	public void enteredIdleState() {
		setEnabledState(0);
	}

	@Override
	public void enteredInProcessState() {
		setEnabledState(1);
	}

	@Override
	public void enteredConnectedState() {
		setEnabledState(2);
	}

	private void setEnabledState(int index) {
		for (ConnectionStateIndication indication : indications) {
			indication.setEnabled(false);
		}
		indications[index].setEnabled(true);
	}

}
