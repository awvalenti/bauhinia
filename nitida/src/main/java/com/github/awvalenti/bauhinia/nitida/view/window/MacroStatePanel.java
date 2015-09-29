package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.forficata.observers.ForficataConnectionStateObserver;

public class MacroStatePanel extends JPanel implements ForficataConnectionStateObserver {

	private static final long serialVersionUID = 1L;

	private final MacroStateIndication[] indications;

	public MacroStatePanel() {
		super(new GridLayout(1, 3));

		indications = new MacroStateIndication[] {
				new MacroStateIndication("Idle", Color.RED),
				new MacroStateIndication("In process", Color.YELLOW),
				new MacroStateIndication("Connected", Color.GREEN),
		};

		for (MacroStateIndication i : indications) {
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
		for (MacroStateIndication indication : indications) {
			indication.setEnabled(false);
		}
		indications[index].setEnabled(true);
	}

}
