package com.github.awvalenti.forficata.implementation.bluecove;

import javax.bluetooth.RemoteDevice;

interface DeviceFoundListener {

	void deviceFound(RemoteDevice btDevice);

}