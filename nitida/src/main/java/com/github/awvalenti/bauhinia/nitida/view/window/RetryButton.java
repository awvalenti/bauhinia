package com.github.awvalenti.bauhinia.nitida.view.window;

import javax.swing.JButton;

import com.github.awvalenti.bauhinia.forficata.observers.ForficataConnectionStateObserver;

public class RetryButton extends JButton implements ForficataConnectionStateObserver {

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
