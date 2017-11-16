package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;

class WiiRemoteConnection {

	private final L2CAPConnection controlPipe;
	private final L2CAPConnection dataPipe;

	WiiRemoteConnection(L2CAPConnection controlPipe,
			L2CAPConnection dataPipe) {
		this.controlPipe = controlPipe;
		this.dataPipe = dataPipe;
	}

	void send(byte[] bytes) throws IOException {
		dataPipe.send(bytes);
	}

	void receive(byte[] bytes) throws IOException {
		dataPipe.receive(bytes);
	}

	boolean isInputReady() throws IOException {
		return dataPipe.ready();
	}

	void close() {
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
