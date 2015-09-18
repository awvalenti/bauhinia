package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;

import com.github.awvalenti.bauhinia.forficata.api.Wiimote;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteButtonListener;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteDisconnectionListener;

class L2CAPWiimote implements Wiimote {

	private final L2CAPConnection input;
	private final L2CAPConnection output;

	private boolean listenerIsSet = false;
	private int litLedIndex = -1;

	private static final byte COMMAND_LED_OR_VIBRATION = 0x11;

	public L2CAPWiimote(L2CAPConnection input, L2CAPConnection output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public void turnLedOn(int ledIndex) throws IOException {
		sendDataToWiimote(COMMAND_LED_OR_VIBRATION, new byte[] { (byte) (1 << (ledIndex % 4 + 4)) });
		this.litLedIndex = ledIndex;
	}

	@Override
	public void startVibration() throws IOException {
		sendDataToWiimote(COMMAND_LED_OR_VIBRATION, new byte[] { (byte) (0x01 | (1 << (litLedIndex % 4 + 4))) });
	}

	@Override
	public void stopVibration() throws IOException {
		turnLedOn(litLedIndex);
	}

	@Override
	public synchronized void setButtonListener(WiimoteButtonListener listener) {
		if (listenerIsSet) throw new IllegalStateException("Button listener is already set");
		listenerIsSet = true;
		new ButtonHandlerThread(input, output, listener).start();
	}

	@Override
	public void setDisconnectionListener(WiimoteDisconnectionListener disconnectionListener) {
		// TODO
	}

	private void sendDataToWiimote(byte commandCode, byte[] data) throws IOException {
		byte[] dataWithExtraBytes = new byte[2 + data.length];
		dataWithExtraBytes[0] = 0x52;
		dataWithExtraBytes[1] = commandCode;
		System.arraycopy(data, 0, dataWithExtraBytes, 2, data.length);
		output.send(dataWithExtraBytes);
	}

}
