package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.bluetooth.L2CAPConnection;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;

class ButtonHandlerThread extends Thread {

	private static final AtomicInteger id = new AtomicInteger(0);

	private final L2CAPConnection input;
	private final L2CAPConnection output;
	private final CoronataButtonObserver buttonObserver;
	private final CoronataDisconnectionObserver disconnectionObserver;

	private byte[] previousState = new byte[4];
	private byte[] currentState = new byte[4];

	private volatile boolean disconnectionRequested = false;

	public ButtonHandlerThread(L2CAPConnection input, L2CAPConnection output,
			CoronataButtonObserver buttonObserver,
			CoronataDisconnectionObserver disconnectionObserver) {
		this.input = input;
		this.output = output;
		this.buttonObserver = buttonObserver;
		this.disconnectionObserver = disconnectionObserver;

		setName(getClass().getSimpleName() + "-" + id.getAndAdd(1));

		// Makes this a user thread instead of a daemon thread.
		// This avoids program exiting when this thread is still running.
		// Setting daemon to false is necessary because the thread that
		// spawns this one is of daemon type, thus this one is also
		// set to dameon by default.
		setDaemon(false);
	}

	public void disconnectWiiRemote() {
		disconnectionRequested = true;
	}

	@Override
	public void run() {
		try {
			for (;;) {
				input.receive(currentState);
				handleButtons();
				swapBuffers();
				while (!input.ready()) {
					if (disconnectionRequested) return;
					Thread.sleep(1);
				}
			}
		} catch (InterruptedException e) {
			// Won't happen (Thread.sleep requires this catch clause)

		} catch (IOException e) {
			// Wii Remote disconnected forcibly
			// (pressed power button, removed batteries, discharged batteries,
			// gone too far from computer etc.)

		} catch (Throwable t) {
			// Observer has thrown unhandled exception (see issue #22)
			throw new RuntimeException(t);

		} finally {
			handleDisconnection();
		}
	}

	private void handleButtons() {
		for (CoronataWiiRemoteButton b : CoronataWiiRemoteButton.values()) {
			if (buttonJustPressed(b)) buttonObserver.buttonPressed(b);
			else if (buttonJustReleased(b)) buttonObserver.buttonReleased(b);
		}
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

	private void handleDisconnection() {
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
