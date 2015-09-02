package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;

import com.github.awvalenti.bauhinia.forficata.api.WiimoteButton;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteListener;

class ButtonHandlerThread extends Thread {
	private final L2CAPConnection input;
	private final L2CAPConnection output;
	private final WiimoteListener listener;

	private byte[] previousState = new byte[4];
	private byte[] currentState = new byte[4];

	public ButtonHandlerThread(L2CAPConnection input, L2CAPConnection output,
			WiimoteListener listener) {
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
