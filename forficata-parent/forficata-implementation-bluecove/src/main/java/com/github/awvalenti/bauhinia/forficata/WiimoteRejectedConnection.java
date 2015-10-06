package com.github.awvalenti.bauhinia.forficata;

import java.io.IOException;

class WiimoteRejectedConnection extends Exception {

	private static final long serialVersionUID = 1L;

	public WiimoteRejectedConnection(IOException e) {
		super(e);
	}

	@Override
	public synchronized IOException getCause() {
		return (IOException) super.getCause();
	}

}
