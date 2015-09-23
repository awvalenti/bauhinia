package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.RemoteDevice;

import com.github.awvalenti.bauhinia.forficata.api.ForficataCallback;
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
	public void run(final ForficataCallback callback) {
		try {
			blueCoveLib.startSynchronousSearch(new DeviceFoundListener() {
				@Override
				public synchronized void deviceFound(RemoteDevice device, DeviceClass deviceClass) {
					callback.bluetoothDeviceFound(device.getBluetoothAddress(), ((Object) deviceClass).toString());
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
			callback.searchStarted();

		} catch (BluetoothStateException e) {
			callback.errorOccurred(ForficataBlueCoveException.correspondingTo(e));
		}
	}

}
