package com.github.awvalenti.bauhinia.forficata;

import java.io.IOException;

import javax.bluetooth.RemoteDevice;

class BluetoothDeviceIdentifier {

	public boolean isWiimote(RemoteDevice device) throws ForficataException {
		try {
			return device.getFriendlyName(false).startsWith("Nintendo RVL-CNT-01");
		} catch (IOException e) {
			throw ForficataExceptionFactory.deviceRejectedIdentification(e);
		}
	}

}
