package com.github.awvalenti.bauhinia.forficata;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;
import javax.bluetooth.RemoteDevice;
import javax.microedition.io.Connector;

import com.github.awvalenti.bauhinia.forficata.listeners.ForficataWiimoteFullListener;

class L2CAPWiimoteFactory {

	public Wiimote createWiimote(RemoteDevice device, ForficataWiimoteFullListener listener)
			throws ForficataException {
		L2CAPConnection input = null;
		L2CAPConnection output = null;

		try {
			String btAddress = device.getBluetoothAddress();

			input = (L2CAPConnection) Connector.open(String.format("btl2cap://%s:13", btAddress),
					Connector.READ, true);

			output = (L2CAPConnection) Connector.open(String.format("btl2cap://%s:11", btAddress),
					Connector.WRITE, true);

			return new L2CAPWiimote(input, output, listener);

		} catch (IOException e1) {
			closeConnectionIfOpen(input);
			throw ForficataExceptionFactory.wiimoteRejectedConnection(e1);
		}
	}

	private void closeConnectionIfOpen(L2CAPConnection connection) {
		try {
			if (connection != null) connection.close();
		} catch (IOException e) {
		}
	}

}
