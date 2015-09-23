package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.RemoteDevice;

import com.github.awvalenti.bauhinia.forficata.api.ForficataCallback;
import com.github.awvalenti.bauhinia.forficata.api.Wiimote;
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
		asyncSearch(callback);
	}

	public void syncSearch(final ForficataCallback callback) {
		asyncSearch(callback);
//		synchronized (monitor) {
//			monitor.notify();
//		}
	}

	private void asyncSearch(final ForficataCallback callback) {
		try {
			blueCoveLib.startAsynchronousSearch(new Listener(callback));
			callback.searchStarted();
		} catch (BluetoothStateException e) {
			callback.errorOccurred(ForficataBlueCoveException.correspondingTo(e));
		}
	}

	private class Listener implements BlueCoveListener {
		private final ForficataCallback callback;

		private Listener(ForficataCallback callback) {
			this.callback = callback;
		}

		@Override
		public synchronized void deviceFound(RemoteDevice device, DeviceClass deviceClass) {
			callback.bluetoothDeviceFound(device.getBluetoothAddress(),
					((Object) deviceClass).toString());
			try {
				if (!factory.deviceIsWiimote(device)) {
					callback.notWiimote();
				} else {
					callback.wiimoteFound();
					Wiimote wiimote = factory.createWiimote(device);
					callback.wiimoteConnected(wiimote);
					if (++numberOfWiimotesFound >= maximumNumberOfWiimotes) {
						blueCoveLib.stopSearch();
					}
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		@Override
		public void searchFinished() {
			callback.searchFinished();
		}
	}

}
