package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;

public class WiiRemoteConnection {

	private final L2CAPConnection controlPipe;
	private final L2CAPConnection dataPipe;

	public WiiRemoteConnection(L2CAPConnection controlPipe,
			L2CAPConnection dataPipe) {
		this.controlPipe = controlPipe;
		this.dataPipe = dataPipe;
	}

	public void send(byte[] bytes) throws IOException {
		dataPipe.send(bytes);
	}

	public void receive(byte[] bytes) throws IOException {
		dataPipe.receive(bytes);
	}

	public boolean isInputReady() throws IOException {
		return dataPipe.ready();
	}

	public void close() {
		try {
			try {
				controlPipe.close();
			} finally {
				dataPipe.close();
			}
		} catch (IOException e) {
			// Nothing can be done here
		}
	}

}
