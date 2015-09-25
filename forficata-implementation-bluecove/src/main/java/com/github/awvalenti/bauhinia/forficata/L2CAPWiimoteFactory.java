package com.github.awvalenti.bauhinia.forficata;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;
import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;

import com.github.awvalenti.bauhinia.forficata.Wiimote;

class L2CAPWiimoteFactory {

	public boolean deviceIsWiimote(RemoteDevice device) throws IOException {
		return device.getFriendlyName(false).startsWith("Nintendo RVL-CNT-01");
	}

	public Wiimote createWiimote(RemoteDevice device) throws IOException {
		String baseAddress = "btl2cap://" + device.getBluetoothAddress();
		L2CAPConnection input = (L2CAPConnection) Connector.open(baseAddress + ":13",
				Connector.READ, true);
		L2CAPConnection output = (L2CAPConnection) Connector.open(baseAddress + ":11",
				Connector.WRITE, true);
		return new L2CAPWiimote(input, output);
	}

}
