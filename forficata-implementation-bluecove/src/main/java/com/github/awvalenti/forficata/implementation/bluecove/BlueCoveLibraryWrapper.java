package com.github.awvalenti.forficata.implementation.bluecove;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.intel.bluetooth.BlueCoveConfigProperties;

class BlueCoveLibraryWrapper {

	public BlueCoveLibraryWrapper() {
		System.setProperty(BlueCoveConfigProperties.PROPERTY_JSR_82_PSM_MINIMUM_OFF, "true");
	}

	public void searchSynchronously(final DeviceFoundListener deviceFoundListener)
			throws BluetoothStateException {

		if (!LocalDevice.isPowerOn()) throw new BluetoothStateException("Bluetooth adapter is off");

		final Object monitor = new Object();

		DiscoveryAgent agent = LocalDevice.getLocalDevice().getDiscoveryAgent();
		agent.startInquiry(DiscoveryAgent.GIAC, new DiscoveryListener() {
			@Override
			public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
			}

			@Override
			public void serviceSearchCompleted(int transID, int respCode) {
			}

			@Override
			public void inquiryCompleted(int discType) {
				synchronized (monitor) {
					monitor.notify();
				}
			}

			@Override
			public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
				deviceFoundListener.deviceFound(btDevice);
			}
		});

		try {
			synchronized (monitor) {
				monitor.wait();
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
