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

class BlueCoveWiimoteConnector implements WiimoteConnector {

	protected final BlueCoveLibraryFacade blueCoveLib = new BlueCoveLibraryFacade();
	protected final L2CAPWiimoteFactory factory = new L2CAPWiimoteFactory();

	private final int maximumNumberOfWiimotes;
	private final boolean synchronous;

	private int numberOfWiimotesFound = 0;

	public BlueCoveWiimoteConnector(int maximumNumberOfWiimotes, boolean synchronous) {
		this.maximumNumberOfWiimotes = maximumNumberOfWiimotes;
		this.synchronous = synchronous;
	}

	@Override
	public void startSearch(final ForficataListener forficataListener) {
		try {
			Object monitor = new Object();
			blueCoveLib.startAsynchronousSearch(new BlueCoveListener(forficataListener, monitor));
			forficataListener.searchStarted();
			if (synchronous) {
				synchronized (monitor) {
					monitor.wait();
				}
			}

		} catch (BluetoothStateException e) {
			forficataListener.errorOccurred(ForficataExceptionFactory.correspondingTo(e));

		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private class BlueCoveListener implements DiscoveryListener {
		private final ForficataListener listener;
		private Object monitor;

		public BlueCoveListener(ForficataListener listener, Object monitor) {
			this.listener = listener;
			this.monitor = monitor;
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
				listener.errorOccurred(ForficataExceptionFactory.connectionRefused(e));
			}
		}

		@Override
		public void inquiryCompleted(int reason) {
			listener.searchFinished();
			synchronized (monitor) {
				monitor.notify();
			}
		}

		@Override
		public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
		}

		@Override
		public void serviceSearchCompleted(int transID, int respCode) {
		}

	}

}
