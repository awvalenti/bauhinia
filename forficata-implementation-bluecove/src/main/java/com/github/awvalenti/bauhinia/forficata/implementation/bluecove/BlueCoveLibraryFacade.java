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

	public void startAsynchronousSearch(final BlueCoveListener blueCoveListener) throws BluetoothStateException {
		agent = LocalDevice.getLocalDevice().getDiscoveryAgent();

		discoveryListener = new DiscoveryListener() {
			@Override
			public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
			}

			@Override
			public void serviceSearchCompleted(int transID, int respCode) {
			}

			@Override
			public void inquiryCompleted(int reason) {
				blueCoveListener.searchFinished();
			}

			@Override
			public void deviceDiscovered(RemoteDevice device, DeviceClass deviceClass) {
				blueCoveListener.deviceFound(device, deviceClass);
			}
		};

		agent.startInquiry(DiscoveryAgent.GIAC, discoveryListener);
	}

	public void stopSearch() {
		agent.cancelInquiry(discoveryListener);
	}

}
