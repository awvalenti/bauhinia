package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.intel.bluetooth.BlueCoveConfigProperties;

class BlueCoveLibraryFacade {

	private DiscoveryAgent agent;
	private DiscoveryListener discoveryListener;

	public BlueCoveLibraryFacade() {
		System.setProperty(BlueCoveConfigProperties.PROPERTY_JSR_82_PSM_MINIMUM_OFF, "true");
	}

	public void startSynchronousSearch(final DeviceFoundListener deviceFoundListener) throws BluetoothStateException {

		agent = LocalDevice.getLocalDevice().getDiscoveryAgent();

		final Object monitor = new Object();

		discoveryListener = new DiscoveryListener() {
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
			public void deviceDiscovered(RemoteDevice device, DeviceClass deviceClass) {
				deviceFoundListener.deviceFound(device, deviceClass);
			}
		};

		agent.startInquiry(DiscoveryAgent.GIAC, discoveryListener);

		waitForInquiryToFinish(monitor);

	}

	private void waitForInquiryToFinish(final Object monitor) {
		try {
			synchronized (monitor) {
				monitor.wait();
			}
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public void stopSearch() {
		agent.cancelInquiry(discoveryListener);
	}

}
