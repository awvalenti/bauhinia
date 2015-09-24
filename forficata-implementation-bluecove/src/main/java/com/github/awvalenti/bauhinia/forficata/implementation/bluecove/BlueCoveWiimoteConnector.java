package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.github.awvalenti.bauhinia.forficata.api.ForficataCallback;
import com.github.awvalenti.bauhinia.forficata.api.Wiimote;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnector;

public abstract class BlueCoveWiimoteConnector implements WiimoteConnector {

	protected final BlueCoveLibraryFacade blueCoveLib = new BlueCoveLibraryFacade();
	protected final L2CAPWiimoteFactory factory = new L2CAPWiimoteFactory();

	private final int maximumNumberOfWiimotes;
	private int numberOfWiimotesFound = 0;

	public BlueCoveWiimoteConnector(int maximumNumberOfWiimotes) {
		this.maximumNumberOfWiimotes = maximumNumberOfWiimotes;
	}

	@Override
	public abstract void run(final ForficataCallback callback);

	protected final void runAsyncSearch(final ForficataCallback callback, Runnable onFinish) {
		try {
			blueCoveLib.startAsynchronousSearch(new BlueCoveListener(callback, onFinish));
			callback.searchStarted();
		} catch (BluetoothStateException e) {
			callback.errorOccurred(ForficataExceptionFactory.correspondingTo(e));
		}
	}

	private class BlueCoveListener implements DiscoveryListener {
		private final ForficataCallback callback;
		private final Runnable additionalActionOnFinish;

		public BlueCoveListener(ForficataCallback callback, Runnable additionalActionOnFinish) {
			this.callback = callback;
			this.additionalActionOnFinish = additionalActionOnFinish;
		}

		@Override
		public synchronized void deviceDiscovered(RemoteDevice device, DeviceClass clazz) {
			callback.identifyingBluetoothDevice(device.getBluetoothAddress(),
					((Object) clazz).toString());
			try {
				if (factory.deviceIsWiimote(device)) {
					callback.wiimoteFound();
					Wiimote wiimote = factory.createWiimote(device);
					callback.wiimoteConnected(wiimote);
					if (++numberOfWiimotesFound >= maximumNumberOfWiimotes) {
						blueCoveLib.stopSearch();
					}
				}
			} catch (IOException e) {
				callback.errorOccurred(ForficataExceptionFactory.unknownError(e));
			}
		}

		@Override
		public void inquiryCompleted(int reason) {
			callback.searchFinished();
			additionalActionOnFinish.run();
		}

		@Override
		public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
		}

		@Override
		public void serviceSearchCompleted(int transID, int respCode) {
		}

	}

}
