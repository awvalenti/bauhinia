package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.intel.bluetooth.BlueCoveConfigProperties;

class BlueCoveLibraryFacade {

	private final DiscoveryAgent agent;
	private DiscoveryListener fullListener;

	public BlueCoveLibraryFacade() throws BluetoothStateException {
		System.setProperty(
				BlueCoveConfigProperties.PROPERTY_JSR_82_PSM_MINIMUM_OFF,
				"true");
		agent = LocalDevice.getLocalDevice().getDiscoveryAgent();
	}

	public void startSynchronousSearch(final SimpleDiscoveryListener l)
			throws BluetoothStateException {

		final Object searchFinishedMonitor = new Object();

		fullListener = new DiscoveryListener() {
			@Override
			public void servicesDiscovered(int transID,
					ServiceRecord[] servRecord) {
			}

			@Override
			public void serviceSearchCompleted(int transID, int respCode) {
			}

			@Override
			public void inquiryCompleted(int discType) {
				synchronized (searchFinishedMonitor) {
					searchFinishedMonitor.notify();
				}
			}

			@Override
			public void deviceDiscovered(RemoteDevice btDevice,
					DeviceClass clazz) {
				l.deviceDiscovered(btDevice, clazz);
			}
		};

		agent.startInquiry(DiscoveryAgent.GIAC, fullListener);

		synchronized (searchFinishedMonitor) {
			try {
				searchFinishedMonitor.wait();
			} catch (InterruptedException e) {
				// Won't happen
			}
		}
	}

	public void stopSearch() {
		agent.cancelInquiry(fullListener);
	}

}
