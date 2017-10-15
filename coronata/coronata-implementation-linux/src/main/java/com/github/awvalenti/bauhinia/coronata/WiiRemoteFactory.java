package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;
import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;

class WiiRemoteFactory {

	private final CoronataButtonObserver buttonObserver;
	private final CoronataDisconnectionObserver disconnectionObserver;

	WiiRemoteFactory(CoronataButtonObserver buttonObserver,
			CoronataDisconnectionObserver disconnectionObserver) {
		this.buttonObserver = buttonObserver;
		this.disconnectionObserver = disconnectionObserver;
	}

	void assertDeviceIsWiiRemote(RemoteDevice device)
			throws IdentificationRejected, IdentifiedAsNonWiiRemote {

		final boolean isWiiRemote;

		try {
			String name = device.getFriendlyName(false);
			isWiiRemote =
					name != null && name.startsWith("Nintendo RVL-CNT-01");
		} catch (IOException e) {
			throw new IdentificationRejected();
		}

		if (!isWiiRemote) throw new IdentifiedAsNonWiiRemote();
	}

	BlueCoveWiiRemote create(String btAddress) throws ConnectionRejected {
		L2CAPConnection input = null;
		try {
			input = (L2CAPConnection) Connector.open(
					String.format("btl2cap://%s:13", btAddress), Connector.READ,
					true);

			L2CAPConnection output = (L2CAPConnection) Connector.open(
					String.format("btl2cap://%s:11", btAddress),
					Connector.WRITE, true);

			return new BlueCoveWiiRemote(input, output, buttonObserver,
					disconnectionObserver);

		} catch (IOException e1) {
			try {
				if (input != null) input.close();
			} catch (IOException e2) {
				// Nothing can be done here
			}
			throw new ConnectionRejected();
		}
	}

	static class IdentificationRejected extends Exception {
		private static final long serialVersionUID = 1L;
	}

	static class IdentifiedAsNonWiiRemote extends Exception {
		private static final long serialVersionUID = 1L;
	}

	static class ConnectionRejected extends Exception {
		private static final long serialVersionUID = 1L;
	}

}
