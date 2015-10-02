package com.github.awvalenti.bauhinia.forficata;

import java.io.IOException;

import javax.bluetooth.RemoteDevice;

class BluetoothDeviceIdentifier {

	public boolean isWiimote(RemoteDevice device) throws IOException {
		return device.getFriendlyName(false).startsWith("Nintendo RVL-CNT-01");
	}

}
