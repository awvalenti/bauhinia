package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;

import com.intel.bluetooth.BlueCoveConfigProperties;

class BlueCoveLibraryFacade {

	private final DiscoveryAgent agent;
	private DiscoveryListener discoveryListener;

	public BlueCoveLibraryFacade() throws BluetoothStateException {
		System.setProperty(BlueCoveConfigProperties.PROPERTY_JSR_82_PSM_MINIMUM_OFF, "true");
		agent = LocalDevice.getLocalDevice().getDiscoveryAgent();
	}

	public void startAsynchronousSearch(DiscoveryListener listener) throws BluetoothStateException {
		discoveryListener = listener;
		agent.startInquiry(DiscoveryAgent.GIAC, discoveryListener);
	}

	public void stopSearch() {
		agent.cancelInquiry(discoveryListener);
	}

}
