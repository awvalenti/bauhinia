package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;


class ButtonHandlerThread extends Thread {

	private final L2CAPConnection input;
	private final L2CAPConnection output;
	private final CoronataButtonObserver buttonObserver;
	private final CoronataDisconnectionObserver disconnectionObserver;

	private byte[] previousState = new byte[4];
	private byte[] currentState = new byte[4];

	public ButtonHandlerThread(L2CAPConnection input, L2CAPConnection output,
			CoronataButtonObserver buttonObserver, CoronataDisconnectionObserver disconnectionObserver) {
		this.input = input;
		this.output = output;
		this.buttonObserver = buttonObserver;
		this.disconnectionObserver = disconnectionObserver;
		setDaemon(false);
	}

	@Override
	public void run() {
		try {
			receiveFirstState();
			for (;;) {
				receiveCurrentState();
				for (CoronataWiiRemoteButton b : CoronataWiiRemoteButton.values()) {
					if (buttonJustPressed(b)) buttonObserver.buttonPressed(b);
					if (buttonJustReleased(b)) buttonObserver.buttonReleased(b);
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

	private boolean buttonJustPressed(CoronataWiiRemoteButton b) {
		return !b.isPressedAccordingTo(previousState) && b.isPressedAccordingTo(currentState);
	}

	private boolean buttonJustReleased(CoronataWiiRemoteButton b) {
		return b.isPressedAccordingTo(previousState) && !b.isPressedAccordingTo(currentState);
	}

	private void swapBuffers() {
		byte[] aux = currentState;
		currentState = previousState;
		previousState = aux;
	}

	private void wiiRemoteDisconnected() {
		// Since we're not using Java 7, we need to do this odd try block to
		// make sure connections are closed and observer is notified
		try {
			try {
				input.close();
			} finally {
				output.close();
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			disconnectionObserver.disconnected();
		}
	}

}
