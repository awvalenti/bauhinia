package com.github.awvalenti.bauhinia.test;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;

public class WiimoteIoHandlingThread extends Thread {

	private final L2CAPConnection inputConnection;
	private final L2CAPConnection outputConnection;

	public WiimoteIoHandlingThread(L2CAPConnection inputConnection, L2CAPConnection outputConnection) {
		this.inputConnection = inputConnection;
		this.outputConnection = outputConnection;
		setDaemon(false);
	}

	@Override
	public void run() {
		byte[] data = new byte[4];
		try {
			for (;;) {
				inputConnection.receive(data);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void turnLedOn(int ledIndex) throws IOException {
		sendDataToWiimote(outputConnection, (byte) 0x11, new byte[] { (byte) (1 << (4 + ledIndex)) });
	}

	private void sendDataToWiimote(L2CAPConnection output, byte commandCode, byte[] data)
			throws IOException {
		byte[] dataWithExtraBytes = new byte[2 + data.length];
		dataWithExtraBytes[0] = 0x52;
		dataWithExtraBytes[1] = commandCode;
		System.arraycopy(data, 0, dataWithExtraBytes, 2, data.length);
		output.send(dataWithExtraBytes);
	}

}
