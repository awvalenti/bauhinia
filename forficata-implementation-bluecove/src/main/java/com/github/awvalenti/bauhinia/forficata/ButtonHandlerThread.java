package com.github.awvalenti.bauhinia.forficata;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;

import com.github.awvalenti.bauhinia.forficata.WiimoteButton;
import com.github.awvalenti.bauhinia.forficata.ForficataWiimoteListener;

class ButtonHandlerThread extends Thread {

	private final L2CAPConnection input;
	private final L2CAPConnection output;
	private final ForficataWiimoteListener listener;

	private byte[] previousState = new byte[4];
	private byte[] currentState = new byte[4];

	public ButtonHandlerThread(L2CAPConnection input, L2CAPConnection output,
			ForficataWiimoteListener listener) {
		this.input = input;
		this.output = output;
		this.listener = listener;
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
			// Happens when Wiimote is disconnected. Handled on finally block below.

		} finally {
			// Gets here when either:
			// - an IOException has occurred (see above)
			// - buttonPressed or buttonReleased has thrown an unchecked exception
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
