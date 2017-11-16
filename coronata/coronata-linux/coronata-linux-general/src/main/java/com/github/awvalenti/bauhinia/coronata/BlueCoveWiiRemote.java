package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;

class BlueCoveWiiRemote implements CoronataWiiRemote, WiiRemoteConstants {

	private final WiiRemoteConnection connection;
	private final ButtonHandlerThread thread;

	private transient byte currentState = 0x00;

	BlueCoveWiiRemote(WiiRemoteConnection connection,
			CoronataButtonObserver buttonObserver,
			CoronataDisconnectionObserver disconnectionObserver) {
		this.connection = connection;
		this.thread = new ButtonHandlerThread(connection, buttonObserver,
				disconnectionObserver);
		this.thread.start();
	}

	@Override
	public void setLightedLEDs(int ledsState) {
		ledsState &= LEDS_MASK;			// Filters undesired bits (if present)

		byte state = currentState;		// Copies state (for thread-safety)
		state &= ~LEDS_MASK;			// Mark all LEDs as off
		state |= ledsState;				// Mark desired LEDs as on

		currentState = state;			// Update class field

		realizeLedsAndOrVibration();	// Realize changes
	}

	@Override
	public void startVibration() {
		currentState |= VIBRATION_MASK;
		realizeLedsAndOrVibration();
	}

	@Override
	public void stopVibration() {
		currentState &= ~VIBRATION_MASK;
		realizeLedsAndOrVibration();
	}

	private void realizeLedsAndOrVibration() {
		try {
			connection.send(new byte[] { OUTPUT_REPORT, ID_LEDS_VIBRATION,
					currentState });
		} catch (IOException e) {
			// This should happen only if user tries to send data to
			// an already disconnected Wii Remote. The exception is ignored.
		}
	}

	@Override
	public void disconnect() {
		thread.disconnectWiiRemote();
	}

}
