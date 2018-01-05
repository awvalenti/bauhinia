package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction;

import javax.swing.JButton;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleStateObserver;

public class StopButton extends JButton implements
		CoronataLifecycleStateObserver {

	private static final long serialVersionUID = 1L;

	public StopButton() {
		super("Stop");
		enteredIdleState();
	}

	@Override
	public void enteredIdleState() {
		setEnabled(false);
	}

	@Override
	public void enteredInProcessState() {
		setEnabled(true);
	}

	@Override
	public void enteredConnectedState() {
		setEnabled(true);
	}

}
