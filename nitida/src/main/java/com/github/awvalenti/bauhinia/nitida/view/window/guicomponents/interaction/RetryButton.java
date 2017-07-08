package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction;

import javax.swing.JButton;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleStateObserver;

public class RetryButton extends JButton implements CoronataLifecycleStateObserver {

	private static final long serialVersionUID = 1L;

	public RetryButton() {
		super("Retry");
	}

	@Override
	public void enteredIdleState() {
		setEnabled(true);
	}

	@Override
	public void enteredInProcessState() {
		setEnabled(false);
	}

	@Override
	public void enteredConnectedState() {
		setEnabled(false);
	}

}
