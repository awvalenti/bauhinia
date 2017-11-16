package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;

class ButtonHandlerThread extends Thread {

	private static final AtomicInteger threadId = new AtomicInteger(0);

	private final WiiRemoteConnection connection;
	private final CoronataButtonObserver buttonObserver;
	private final CoronataDisconnectionObserver disconnectionObserver;

	private byte[] previousState = new byte[4];
	private byte[] currentState = new byte[4];

	private volatile boolean disconnectionRequested = false;

	public ButtonHandlerThread(WiiRemoteConnection connection,
			CoronataButtonObserver buttonObserver,
			CoronataDisconnectionObserver disconnectionObserver) {

		setName(getClass().getSimpleName() + "-" + threadId.getAndIncrement());

		this.connection = connection;
		this.buttonObserver = buttonObserver;
		this.disconnectionObserver = disconnectionObserver;
	}

	public void disconnectWiiRemote() {
		disconnectionRequested = true;
	}

	@Override
	public void run() {
		try {
			receiveFirstState();
			for (;;) {
				waitForDataReady();
				if (disconnectionRequested) break;
				receiveCurrentState();
				handleButtons();
				swapBuffers();
			}
		} catch (InterruptedException e) {
			// Won't happen (Thread.sleep requires this catch clause)

		} catch (IOException e) {
			// Wii Remote disconnected forcibly
			// (pressed power button, removed batteries, discharged batteries,
			// gone too far from computer etc.)

		} catch (Throwable t) {
			// buttonObserver has thrown unhandled exception
			// (see https://github.com/awvalenti/bauhinia/issues/48)
			throw new RuntimeException(t);

		} finally {
			try {
				connection.close();
			} finally {
				disconnectionObserver.disconnected();
			}
		}
	}

	private void receiveFirstState() throws IOException {
		connection.receive(previousState);
	}

	private void waitForDataReady() throws IOException, InterruptedException {
		while (!connection.isInputReady()) {
			if (disconnectionRequested) break;
			Thread.sleep(1);
		}
	}

	private void receiveCurrentState() throws IOException {
		connection.receive(currentState);
	}

	private void handleButtons() {
		for (CoronataWiiRemoteButton b : CoronataWiiRemoteButton.values()) {
			if (buttonJustPressed(b)) buttonObserver.buttonPressed(b);
			else if (buttonJustReleased(b)) buttonObserver.buttonReleased(b);
		}
	}

	private boolean buttonJustPressed(CoronataWiiRemoteButton b) {
		return !b.isPressedAccordingTo(previousState) &&
				b.isPressedAccordingTo(currentState);
	}

	private boolean buttonJustReleased(CoronataWiiRemoteButton b) {
		return b.isPressedAccordingTo(previousState) &&
				!b.isPressedAccordingTo(currentState);
	}

	private void swapBuffers() {
		byte[] aux = currentState;
		currentState = previousState;
		previousState = aux;
	}

}
