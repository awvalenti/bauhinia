package com.github.awvalenti.bauhinia.test;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;

import com.intel.bluetooth.BlueCoveConfigProperties;

public class BlueCoveLibrary {

	public BlueCoveLibrary() {
		System.setProperty(BlueCoveConfigProperties.PROPERTY_JSR_82_PSM_MINIMUM_OFF, "true");
	}

	public void startInquiry(final DeviceFoundListener listener, final Object lock)
			throws BluetoothStateException {
		LocalDevice.getLocalDevice().getDiscoveryAgent()
				.startInquiry(DiscoveryAgent.GIAC, new DiscoveryListener() {
					public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
					}

					public void serviceSearchCompleted(int transID, int respCode) {
					}

					public void inquiryCompleted(int discType) {
						lock.notify();
					}

					public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
						listener.deviceFound(btDevice);
					}
				});
	}

}
