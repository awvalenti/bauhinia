package com.github.awvalenti.bauhinia.forficata;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.github.awvalenti.bauhinia.forficata.ForficataListener;
import com.github.awvalenti.bauhinia.forficata.Wiimote;
import com.github.awvalenti.bauhinia.forficata.WiimoteConnector;

abstract class BlueCoveWiimoteConnector implements WiimoteConnector {

	protected final BlueCoveLibraryFacade blueCoveLib = new BlueCoveLibraryFacade();
	protected final L2CAPWiimoteFactory factory = new L2CAPWiimoteFactory();

	private final int maximumNumberOfWiimotes;
	private int numberOfWiimotesFound = 0;

	public BlueCoveWiimoteConnector(int maximumNumberOfWiimotes) {
		this.maximumNumberOfWiimotes = maximumNumberOfWiimotes;
	}

	@Override
	public abstract void startSearch(final ForficataListener listener);

	protected final void runAsyncSearch(final ForficataListener listener, Runnable onFinish) {
		try {
			blueCoveLib.startAsynchronousSearch(new BlueCoveListener(listener, onFinish));
			listener.searchStarted();
		} catch (BluetoothStateException e) {
			listener.errorOccurred(ForficataExceptionFactory.correspondingTo(e));
		}
	}

	private class BlueCoveListener implements DiscoveryListener {
		private final ForficataListener listener;
		private final Runnable additionalActionOnFinish;

		public BlueCoveListener(ForficataListener listener, Runnable additionalActionOnFinish) {
			this.listener = listener;
			this.additionalActionOnFinish = additionalActionOnFinish;
		}

		@Override
		public synchronized void deviceDiscovered(RemoteDevice device, DeviceClass clazz) {
			listener.identifyingBluetoothDevice(device.getBluetoothAddress(),
					((Object) clazz).toString());
			try {
				if (factory.deviceIsWiimote(device)) {
					listener.wiimoteFound();
					Wiimote wiimote = factory.createWiimote(device);
					listener.wiimoteConnected(wiimote);
					if (++numberOfWiimotesFound >= maximumNumberOfWiimotes) {
						blueCoveLib.stopSearch();
					}
				}
			} catch (IOException e) {
				listener.errorOccurred(ForficataExceptionFactory.unknownError(e));
			}
		}

		@Override
		public void inquiryCompleted(int reason) {
			listener.searchFinished();
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
