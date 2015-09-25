package com.github.awvalenti.bauhinia.forficata;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;

class L2CAPWiimote implements Wiimote {

	private final L2CAPConnection input;
	private final L2CAPConnection output;

	private boolean listenerIsSet = false;
	private byte litLedIndex = -1;
	private byte vibrationState = 0x00;

	public L2CAPWiimote(L2CAPConnection input, L2CAPConnection output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public void turnLedOn(int ledIndex) throws IOException {
		this.litLedIndex = (byte) (ledIndex % 4);
		realizeLedAndOrVibration();
	}

	@Override
	public void startVibration() throws IOException {
		vibrationState = 0x01;
		realizeLedAndOrVibration();
	}

	@Override
	public void stopVibration() throws IOException {
		vibrationState = 0x00;
		realizeLedAndOrVibration();
	}

	@Override
	public synchronized void setButtonListener(WiimoteButtonListener listener) {
		if (listenerIsSet) throw new IllegalStateException("Button listener is already set");
		listenerIsSet = true;
		new ButtonHandlerThread(input, output, listener).start();
	}

	private void realizeLedAndOrVibration() throws IOException {
		sendDataToWiimote((byte) 0x11, new byte[] { (byte) (vibrationState | (1 << (litLedIndex + 4))) });
	}

	private void sendDataToWiimote(byte commandCode, byte[] data) throws IOException {
		byte[] dataWithExtraBytes = new byte[2 + data.length];
		dataWithExtraBytes[0] = 0x52;
		dataWithExtraBytes[1] = commandCode;
		System.arraycopy(data, 0, dataWithExtraBytes, 2, data.length);
		output.send(dataWithExtraBytes);
	}

}
