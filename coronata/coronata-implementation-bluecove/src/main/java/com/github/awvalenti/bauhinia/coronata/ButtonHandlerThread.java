package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;

import com.github.awvalenti.bauhinia.coronata.WiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;

class ButtonHandlerThread extends Thread {

	private final L2CAPConnection input;
	private final L2CAPConnection output;
	private final WiiRemoteFullListener listener;

	private byte[] previousState = new byte[4];
	private byte[] currentState = new byte[4];

	public ButtonHandlerThread(L2CAPConnection input, L2CAPConnection output,
			WiiRemoteFullListener listener) {
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
				for (WiiRemoteButton b : WiiRemoteButton.values()) {
					if (buttonJustPressed(b)) listener.buttonPressed(b);
					if (buttonJustReleased(b)) listener.buttonReleased(b);
				}
				swapBuffers();
			}
		} catch (IOException e) {
			// Happens when Wii Remote is disconnected. Handled on finally block below.

		} finally {
			// Gets here when either:
			// - an IOException has occurred (see above)
			// - buttonPressed or buttonReleased has thrown an unchecked exception
			wiiRemoteDisconnected();
		}
	}

	private void receiveFirstState() throws IOException {
		input.receive(previousState);
	}

	private void receiveCurrentState() throws IOException {
		input.receive(currentState);
	}

	private boolean buttonJustPressed(WiiRemoteButton b) {
		return !b.isPressedAccordingTo(previousState) && b.isPressedAccordingTo(currentState);
	}

	private boolean buttonJustReleased(WiiRemoteButton b) {
		return b.isPressedAccordingTo(previousState) && !b.isPressedAccordingTo(currentState);
	}

	private void swapBuffers() {
		byte[] aux = currentState;
		currentState = previousState;
		previousState = aux;
	}

	private void wiiRemoteDisconnected() {
		// Since we're not using Java 7, we need to do this odd try block to
		// make sure connections are closed and listener is informed
		try {
			try {
				input.close();
			} finally {
				output.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			listener.wiiRemoteDisconnected();
		}
	}

}
