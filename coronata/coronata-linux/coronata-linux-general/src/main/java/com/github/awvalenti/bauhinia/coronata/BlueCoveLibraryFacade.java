package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;

import com.intel.bluetooth.BlueCoveConfigProperties;

class BlueCoveLibraryFacade {

	private final DiscoveryAgent agent;
	private DiscoveryListener listener;

	BlueCoveLibraryFacade() throws BluetoothStateException {
		System.setProperty(
				BlueCoveConfigProperties.PROPERTY_JSR_82_PSM_MINIMUM_OFF,
				"true");
		agent = LocalDevice.getLocalDevice().getDiscoveryAgent();
	}

	void startAsynchronousSearch(DiscoveryListener l)
			throws BluetoothStateException {
		listener = l;
		agent.startInquiry(DiscoveryAgent.GIAC, listener);
	}

	void stopSearch() {
		agent.cancelInquiry(listener);
	}

}
