package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.RemoteDevice;

interface SimpleDiscoveryListener {

	void deviceDiscovered(RemoteDevice btDevice, DeviceClass deviceClass);

}
