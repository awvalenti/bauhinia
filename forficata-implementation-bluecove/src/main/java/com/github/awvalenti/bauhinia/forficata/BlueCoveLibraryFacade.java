package com.github.awvalenti.bauhinia.forficata;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;

import com.intel.bluetooth.BlueCoveConfigProperties;

class BlueCoveLibraryFacade {

	private DiscoveryAgent agent;
	private DiscoveryListener discoveryListener;

	public BlueCoveLibraryFacade() {
		System.setProperty(BlueCoveConfigProperties.PROPERTY_JSR_82_PSM_MINIMUM_OFF, "true");
	}

	public void startAsynchronousSearch(DiscoveryListener listener) throws BluetoothStateException {
		agent = LocalDevice.getLocalDevice().getDiscoveryAgent();
		discoveryListener = listener;
		agent.startInquiry(DiscoveryAgent.GIAC, discoveryListener);
	}

	public void stopSearch() {
		agent.cancelInquiry(discoveryListener);
	}

}
