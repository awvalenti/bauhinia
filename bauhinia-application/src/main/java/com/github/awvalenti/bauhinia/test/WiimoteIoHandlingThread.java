package com.github.awvalenti.bauhinia.test;

import java.io.IOException;
import java.util.Arrays;

import javax.bluetooth.L2CAPConnection;

public class WiimoteIoHandlingThread extends Thread {

	private final L2CAPConnection inputConnection;
	private final L2CAPConnection outputConnection;

	public WiimoteIoHandlingThread(L2CAPConnection inputConnection, L2CAPConnection outputConnection) {
		this.inputConnection = inputConnection;
		this.outputConnection = outputConnection;
	}

	@Override
	public void run() {
		try {
			for (;;) {
				byte[] data = new byte[32];
				inputConnection.receive(data);
				if (buttonWasPressed(data)) {
					System.out.println(Arrays.toString(data));
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static boolean buttonWasPressed(byte[] data) {
		return data[1] == 0x30;
	}

	public void turnLedOn(int ledIndex) throws IOException {
		sendDataToWiimote(outputConnection, (byte) 0x11, new byte[] { (byte) (1 << (4 + ledIndex)) });
	}

	private void sendDataToWiimote(L2CAPConnection output, byte commandCode, byte[] data1)
			throws IOException {
		output.send(withTwoBytesAddedBefore(data1, commandCode));
	}

	private byte[] withTwoBytesAddedBefore(byte[] data1, byte commandCode) {
		byte[] ret = new byte[data1.length + 2];
		ret[0] = 0x52;
		ret[1] = commandCode;
		System.arraycopy(data1, 0, ret, 2, data1.length);
		return ret;
	}

}
