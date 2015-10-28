package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import javax.bluetooth.RemoteDevice;

class BluetoothDeviceIdentifier {

	public void assertDeviceIsWiimote(RemoteDevice device) throws IdentifiedAnotherDevice,
			DeviceRejectedIdentification {
		if (!isWiimote(device)) throw new IdentifiedAnotherDevice();
	}

	private boolean isWiimote(RemoteDevice device) throws DeviceRejectedIdentification {
		try {
			return device.getFriendlyName(false).startsWith("Nintendo RVL-CNT-01");
		} catch (IOException e) {
			throw new DeviceRejectedIdentification(e);
		}
	}

}
