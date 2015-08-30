package com.github.awvalenti.bauhinia.test;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;
import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;

import com.github.awvalenti.bauhinia.forficata.api.Wiimote;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteButton;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteListener;
import com.github.awvalenti.bauhinia.forficata.implementation.L2capWiimote;

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

		Wiimote wiimote = new L2capWiimote(input, output);
		wiimote.turnLedOn(0);
		wiimote.setListener(new WiimoteListener() {
			@Override
			public void wiimoteConnected() {
				System.out.println("Connected");
			}

			@Override
			public void buttonPressed(WiimoteButton button) {
				System.out.printf("Pressed %s\n", button);
			}

			@Override
			public void buttonReleased(WiimoteButton button) {
				System.out.printf("Released %s\n", button);
			}

			@Override
			public void wiimoteDisconnected() {
				System.out.println("Disconnected");
			}
		});
	}

}
