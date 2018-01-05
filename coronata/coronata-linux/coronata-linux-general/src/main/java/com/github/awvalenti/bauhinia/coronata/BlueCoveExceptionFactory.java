package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;

class BlueCoveExceptionFactory {

	public CoronataException correspondingTo(BluetoothStateException cause) {
		String errorMsg = cause.getMessage().toLowerCase();

		if (errorMsg.contains("librar") && errorMsg.contains("not available")) {
			return problemLoadingLibraries(cause);

		} else if (errorMsg.contains("device is not available") ||
				errorMsg.contains("stack not detected")) {
			return bluetoothAdapterIsOff(cause);

		} else if (errorMsg.contains("device is not ready")) {
			return deviceIsNotReady(cause);

		} else {
			return unexpectedError(cause);
		}
	}

	private CoronataException
			problemLoadingLibraries(BluetoothStateException cause) {
		return new CoronataException(cause,
				"Unable to load required libraries for BlueCove.\n" +
				"\tCheck if the requirements described here were met:" +
				" http://www.bluecove.org/bluecove-gpl/index.html\n" +
				"\tTry installing one of these packages:" +
				" libbluetooth-dev (Ubuntu)," +
				" bluez-libs-devel (Fedora), bluez-devel (openSUSE)\n" +
				"");
	}

	private CoronataException
			bluetoothAdapterIsOff(BluetoothStateException cause) {
		return new CoronataException(cause,
				"Bluetooth adapter is unavailable. Make sure it is enabled. " +
				"If it is a USB adapter, check if it is connected." +
				"");
	}

	private CoronataException deviceIsNotReady(BluetoothStateException cause) {
		return new CoronataException(cause,
				"Bluetooth adapter is not ready. Try turning it off and on" +
				" again." +
				"");
	}

	private CoronataException unexpectedError(Exception cause) {
		return new CoronataException(cause, "Unexpected error: " +
				cause.getMessage());
	}

}
