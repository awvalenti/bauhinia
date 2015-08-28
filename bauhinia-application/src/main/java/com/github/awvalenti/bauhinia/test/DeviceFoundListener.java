package com.github.awvalenti.bauhinia.test;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;
import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;

public class DeviceFoundListener {

	public void deviceFound(RemoteDevice btDevice) {
		try {
			if (deviceIsWiimote(btDevice)) {
				connect(btDevice);
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private boolean deviceIsWiimote(RemoteDevice btDevice) throws IOException {
		return btDevice.getFriendlyName(false).startsWith("Nintendo");
	}

	private void connect(RemoteDevice btDevice) throws IOException {
		String baseAddress = "btl2cap://" + btDevice.getBluetoothAddress();
		L2CAPConnection input = (L2CAPConnection) Connector.open(baseAddress + ":13",
				Connector.READ, true);
		L2CAPConnection output = (L2CAPConnection) Connector.open(baseAddress + ":11",
				Connector.WRITE, true);

		WiimoteIoHandlingThread wiimoteIoHandlingThread = new WiimoteIoHandlingThread(input, output);
		wiimoteIoHandlingThread.turnLedOn(0);
		wiimoteIoHandlingThread.start();
	}

}
