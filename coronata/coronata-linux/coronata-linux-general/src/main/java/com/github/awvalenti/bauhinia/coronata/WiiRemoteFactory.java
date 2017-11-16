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
			String n = device.getFriendlyName(false);
			isWiiRemote = n != null && n.startsWith("Nintendo RVL-CNT-01");
		} catch (IOException e) {
			throw new IdentificationRejected();
		}

		if (!isWiiRemote) throw new IdentifiedAsNonWiiRemote();
	}

	BlueCoveWiiRemote create(String btAddress) throws ConnectionRejected {
		// http://wiibrew.org/wiki/Wiimote#HID_Interface

		L2CAPConnection controlPipe = null;
		L2CAPConnection dataPipe;

		try {
			controlPipe = (L2CAPConnection) Connector.open(
					String.format("btl2cap://%s:11", btAddress),
					Connector.WRITE, true);

			dataPipe = (L2CAPConnection) Connector.open(
					String.format("btl2cap://%s:13", btAddress),
					Connector.READ_WRITE, true);

		} catch (IOException e1) {
			try {
				if (controlPipe != null) controlPipe.close();
				// dataPipe is surely not open here

			} catch (IOException e2) {
				// Nothing can be done here
			}

			throw new ConnectionRejected();
		}

		return new BlueCoveWiiRemote(
				new WiiRemoteConnection(controlPipe, dataPipe), buttonObserver,
				disconnectionObserver);
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
