package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import javax.bluetooth.RemoteDevice;

class BluetoothDeviceIdentifier {

	public void assertDeviceIsWiiRemote(RemoteDevice device) throws IdentifiedAnotherDevice,
			DeviceRejectedIdentification {
		if (!isWiiRemote(device)) throw new IdentifiedAnotherDevice();
	}

	private boolean isWiiRemote(RemoteDevice device) throws DeviceRejectedIdentification {
		try {
			return device.getFriendlyName(false).startsWith("Nintendo RVL-CNT-01");
		} catch (IOException e) {
			throw new DeviceRejectedIdentification(e);
		}
	}

}
