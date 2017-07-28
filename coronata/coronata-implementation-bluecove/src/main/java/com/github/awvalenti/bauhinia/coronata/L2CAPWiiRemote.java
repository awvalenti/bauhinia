package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;


class L2CAPWiiRemote implements CoronataWiiRemote {

	private final L2CAPConnection output;

	private byte ledsState = LEDS_NONE;
	private byte vibrationState = 0x00;

	public L2CAPWiiRemote(L2CAPConnection input, L2CAPConnection output, CoronataButtonObserver buttonObserver,
			CoronataDisconnectionObserver disconnectionObserver) {
		this.output = output;
		new ButtonHandlerThread(input, output, buttonObserver, disconnectionObserver).start();
	}

	@Override
	public void setLightedLEDs(int ledsState) {
		this.ledsState = (byte) ledsState;
		realizeLedAndOrVibration();
	}

	@Override
	public void startVibration() {
		vibrationState = 0x01;
		realizeLedAndOrVibration();
	}

	@Override
	public void stopVibration() {
		vibrationState = 0x00;
		realizeLedAndOrVibration();
	}

	private void realizeLedAndOrVibration() {
		try {
			output.send(new byte[] { 0x52, 0x11, (byte) (ledsState << 4 | vibrationState) });
		} catch (IOException e) {
			// This should happen only if user tries to send data to
			// an already disconnected Wii Remote. The exception is ignored.
		}
	}

}
