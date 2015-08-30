package com.github.awvalenti.forficata.implementation.bluecove;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.RemoteDevice;

import com.github.awvalenti.forficata.api.WiimoteConnectedCallback;
import com.github.awvalenti.forficata.api.WiimoteConnector;

public class BlueCoveWiimoteConnector implements WiimoteConnector {

	private final BlueCoveLibraryWrapper blueCoveLib = new BlueCoveLibraryWrapper();
	private final WiimoteFactory factory = new WiimoteFactory();

	@Override
	public void searchAndConnect(final WiimoteConnectedCallback callback) {
		try {
			blueCoveLib.searchSynchronously(new DeviceFoundListener() {
				@Override
				public void deviceFound(RemoteDevice device) {
					try {
						if (factory.deviceIsWiimote(device)) {
							callback.wiimoteConnected(factory.createWiimote(device));
						}
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
				}
			});
		} catch (BluetoothStateException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void connectToWiimoteAt(String bluetoothAddress, WiimoteConnectedCallback callback) {
	}

}
