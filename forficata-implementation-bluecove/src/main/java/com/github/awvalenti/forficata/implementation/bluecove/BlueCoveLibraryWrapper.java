package com.github.awvalenti.forficata.implementation.bluecove;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.github.awvalenti.forficata.api.ForficataException;
import com.intel.bluetooth.BlueCoveConfigProperties;

class BlueCoveLibraryWrapper {

	public BlueCoveLibraryWrapper() {
		System.setProperty(BlueCoveConfigProperties.PROPERTY_JSR_82_PSM_MINIMUM_OFF, "true");
	}

	public void searchSynchronously(final DeviceFoundListener deviceFoundListener)
			throws ForficataException {

		try {
			DiscoveryAgent agent = LocalDevice.getLocalDevice().getDiscoveryAgent();

			final Object monitor = new Object();

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

			waitForInquiryToFinish(monitor);

		} catch (BluetoothStateException e) {
			throw ForficataBlueCoveException.correspondingTo(e);
		}
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

}
