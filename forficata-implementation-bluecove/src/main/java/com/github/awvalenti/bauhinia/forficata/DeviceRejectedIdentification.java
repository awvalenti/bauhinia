package com.github.awvalenti.bauhinia.forficata;

import java.io.IOException;

class DeviceRejectedIdentification extends Exception {

	private static final long serialVersionUID = 1L;

	public DeviceRejectedIdentification(IOException e) {
		super(e);
	}

}
