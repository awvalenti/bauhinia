package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;
import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;


class BlueCoveWiiRemoteFactory {

	public void assertDeviceIsWiiRemote(RemoteDevice device) throws DeviceRejectedIdentification,
			IdentifiedAnotherDevice {
		final boolean isWiiRemote;

		try {
			isWiiRemote = device.getFriendlyName(false).startsWith("Nintendo RVL-CNT-01");
		} catch (IOException e) {
			throw new DeviceRejectedIdentification();
		}

		if (!isWiiRemote) throw new IdentifiedAnotherDevice();
	}

	public CoronataWiiRemote createWiiRemote(RemoteDevice device, CoronataButtonObserver buttonObserver,
			CoronataDisconnectionObserver disconnectionObserver) throws WiiRemoteRejectedConnection {
		String btAddress = device.getBluetoothAddress();

		L2CAPConnection input = null;

		try {
			input = (L2CAPConnection) Connector.open(String.format("btl2cap://%s:13", btAddress),
					Connector.READ, true);

			L2CAPConnection output = (L2CAPConnection) Connector.open(
					String.format("btl2cap://%s:11", btAddress), Connector.WRITE, true);

			return new BlueCoveWiiRemote(input, output, buttonObserver, disconnectionObserver);

		} catch (IOException e1) {
			try {
				if (input != null) input.close();
			} catch (IOException e2) {
				// If exception happens when trying to close input, nothing can be done
			}
			throw new WiiRemoteRejectedConnection(e1);
		}
	}

	static class IdentifiedAnotherDevice extends Exception {
		private static final long serialVersionUID = 1L;
	}

	static class DeviceRejectedIdentification extends Exception {
		private static final long serialVersionUID = 1L;
	}

	static class WiiRemoteRejectedConnection extends Exception {
		private static final long serialVersionUID = 1L;

		public WiiRemoteRejectedConnection(IOException e) {
			super(e);
		}

		@Override
		public IOException getCause() {
			return (IOException) super.getCause();
		}
	}

}
