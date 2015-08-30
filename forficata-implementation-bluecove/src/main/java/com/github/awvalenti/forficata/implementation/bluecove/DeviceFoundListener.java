package com.github.awvalenti.forficata.implementation.bluecove;

import javax.bluetooth.RemoteDevice;

public interface DeviceFoundListener {

	void deviceFound(RemoteDevice btDevice);

}