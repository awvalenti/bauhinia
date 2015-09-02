package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import java.io.IOException;

import javax.bluetooth.RemoteDevice;

import com.github.awvalenti.bauhinia.forficata.api.ForficataException;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnectedCallback;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnector;

public class BlueCoveWiimoteConnector implements WiimoteConnector {

	private final BlueCoveLibraryWrapper blueCoveLib = new BlueCoveLibraryWrapper();
	private final WiimoteFactory factory = new WiimoteFactory();

	@Override
	public void searchAndConnect(final WiimoteConnectedCallback callback) throws ForficataException {
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
	}

	@Override
	public void connectToWiimoteAt(String bluetoothAddress, WiimoteConnectedCallback callback) {
	}

}
