package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import java.io.IOException;

import javax.bluetooth.RemoteDevice;

import com.github.awvalenti.bauhinia.forficata.api.ForficataException;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnectedCallback;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnector;

public class BlueCoveWiimoteConnector implements WiimoteConnector {

	private final BlueCoveLibraryFacade blueCoveLib = new BlueCoveLibraryFacade();
	private final L2CAPWiimoteFactory factory = new L2CAPWiimoteFactory();

	private final int maximumNumberOfWiimotes;
	private int numberOfWiimotesFound = 0;

	public BlueCoveWiimoteConnector(int maximumNumberOfWiimotes) {
		this.maximumNumberOfWiimotes = maximumNumberOfWiimotes;
	}

	@Override
	public void searchAndConnect(final WiimoteConnectedCallback callback) throws ForficataException {
		blueCoveLib.startSynchronousSearch(new DeviceFoundListener() {
			@Override
			public synchronized void deviceFound(RemoteDevice device) {
				try {
					if (factory.deviceIsWiimote(device)) {
						callback.wiimoteConnected(factory.createWiimote(device));
						if (++numberOfWiimotesFound >= maximumNumberOfWiimotes) {
							blueCoveLib.stopSearch();
						}
					}
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

	@Override
	public void connectToWiimoteAt(String bluetoothAddress, WiimoteConnectedCallback callback) {
		// TODO
	}

}
