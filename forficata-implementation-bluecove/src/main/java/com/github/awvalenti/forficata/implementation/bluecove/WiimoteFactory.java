package com.github.awvalenti.forficata.implementation.bluecove;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;
import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;

import com.github.awvalenti.forficata.api.Wiimote;

public class WiimoteFactory {

	public boolean deviceIsWiimote(RemoteDevice device) throws IOException {
		return device.getFriendlyName(false).startsWith("Nintendo RVL-CNT-01");
	}

	public Wiimote createWiimote(RemoteDevice device) throws IOException {
		String baseAddress = "btl2cap://" + device.getBluetoothAddress();
		L2CAPConnection input = (L2CAPConnection) Connector.open(baseAddress + ":13",
				Connector.READ, true);
		L2CAPConnection output = (L2CAPConnection) Connector.open(baseAddress + ":11",
				Connector.WRITE, true);
		return new L2capWiimote(input, output);
	}

}
