package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;

import com.github.awvalenti.bauhinia.forficata.api.Wiimote;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteListener;

class L2capWiimote implements Wiimote {

	private final L2CAPConnection input;
	private final L2CAPConnection output;

	public L2capWiimote(L2CAPConnection input, L2CAPConnection output) {
		this.input = input;
		this.output = output;
	}

	@Override
	public void turnLedOn(int ledIndex) throws IOException {
		sendDataToWiimote((byte) 0x11, new byte[] { (byte) (1 << (ledIndex % 4 + 4)) });
	}

	@Override
	public void startVibration() {
		// TODO
	}

	@Override
	public void stopVibration() {
		// TODO
	}

	@Override
	public void addListener(WiimoteListener listener) {
		new ButtonHandlerThread(input, output, listener).start();
	}

	private void sendDataToWiimote(byte commandCode, byte[] data) throws IOException {
		byte[] dataWithExtraBytes = new byte[2 + data.length];
		dataWithExtraBytes[0] = 0x52;
		dataWithExtraBytes[1] = commandCode;
		System.arraycopy(data, 0, dataWithExtraBytes, 2, data.length);
		output.send(dataWithExtraBytes);
	}

}
