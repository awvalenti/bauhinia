package com.github.awvalenti.bauhinia.forficata.implementation.bluecove;

import javax.bluetooth.DeviceClass;
import javax.bluetooth.RemoteDevice;

interface DeviceFoundListener {

	void deviceFound(RemoteDevice btDevice, DeviceClass deviceClass);

}