package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;


class L2CAPWiiRemote implements CoronataWiiRemote, L2CAPWiiRemoteConstants {

	private final L2CAPConnection output;

	private byte ledsState = LEDS_NONE;
	private byte vibrationState = VIBRATION_OFF;

	public L2CAPWiiRemote(L2CAPConnection input, L2CAPConnection output, CoronataButtonObserver buttonObserver,
			CoronataDisconnectionObserver disconnectionObserver) {
		this.output = output;
		new ButtonHandlerThread(input, output, buttonObserver, disconnectionObserver).start();
	}

	@Override
	public void setLightedLEDs(int ledsState) {
		// Only first four bits are important. The other ones should not be set.
		// To avoid interfering with vibration, they are discarded.
		this.ledsState = (byte) (ledsState & 0xF0);

		realizeLedsAndOrVibration();
	}

	@Override
	public void startVibration() {
		vibrationState = VIBRATION_ON;
		realizeLedsAndOrVibration();
	}

	@Override
	public void stopVibration() {
		vibrationState = VIBRATION_OFF;
		realizeLedsAndOrVibration();
	}

	private void realizeLedsAndOrVibration() {
		try {
			output.send(new byte[] { SET_REPORT, ID_LEDS_VIBRATION, (byte) (ledsState | vibrationState) });
		} catch (IOException e) {
			// This should happen only if user tries to send data to
			// an already disconnected Wii Remote. The exception is ignored.
		}
	}

}
