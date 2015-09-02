package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;

import com.github.awvalenti.bauhinia.forficata.api.WiimoteButton;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteButtonListener;

class ButtonHandlerThread extends Thread {

	private final L2CAPConnection input;
	private final L2CAPConnection output;
	private final WiimoteButtonListener listener;

	private byte[] previousState = new byte[4];
	private byte[] currentState = new byte[4];

	public ButtonHandlerThread(L2CAPConnection input, L2CAPConnection output,
			WiimoteButtonListener listener) {
		this.listener = listener;
		this.input = input;
		this.output = output;
		setDaemon(false);
	}

	@Override
	public void run() {
		try {
			receiveFirstState();
			for (;;) {
				receiveCurrentState();
				for (WiimoteButton b : WiimoteButton.values()) {
					if (buttonJustPressed(b)) listener.buttonPressed(b);
					if (buttonJustReleased(b)) listener.buttonReleased(b);
				}
				swapBuffers();
			}
		} catch (IOException e) {
			// Happens when wiimote is disconnected. Handled in finally block below.
		} finally {
			wiimoteDisconnected();
		}
	}

	private void receiveFirstState() throws IOException {
		input.receive(previousState);
	}

	private void receiveCurrentState() throws IOException {
		input.receive(currentState);
	}

	private boolean buttonJustPressed(WiimoteButton b) {
		return !b.isPressedAccordingTo(previousState) && b.isPressedAccordingTo(currentState);
	}

	private boolean buttonJustReleased(WiimoteButton b) {
		return b.isPressedAccordingTo(previousState) && !b.isPressedAccordingTo(currentState);
	}

	private void swapBuffers() {
		byte[] aux = currentState;
		currentState = previousState;
		previousState = aux;
	}

	private void wiimoteDisconnected() {
		// Since we're not using Java 7, we need to do this odd try block to
		// make sure connections are closed and listener is informed
		try {
			try {
				input.close();
			} finally {
				try {
					output.close();
				} finally {
					listener.wiimoteDisconnected();
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
