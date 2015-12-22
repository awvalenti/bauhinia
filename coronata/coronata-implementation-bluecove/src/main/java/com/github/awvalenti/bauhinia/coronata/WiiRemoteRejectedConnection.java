package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

class WiiRemoteRejectedConnection extends Exception {

	private static final long serialVersionUID = 1L;

	public WiiRemoteRejectedConnection(IOException e) {
		super(e);
	}

	@Override
	public synchronized IOException getCause() {
		return (IOException) super.getCause();
	}

}
