package com.github.awvalenti.forficata.implementation.bluecove;

import javax.bluetooth.BluetoothStateException;

import com.github.awvalenti.forficata.api.ForficataException;

class ForficataBlueCoveException {

	public static ForficataException correspondingTo(BluetoothStateException e) {
		String errorMsg = e.getMessage().toLowerCase();

		if (errorMsg.contains("librar") && errorMsg.contains("not available")) {
			return problemLoadingLibraries(e);

		} else if (errorMsg.contains("device is not available") || errorMsg.contains("stack not detected")) {
			return bluetoothAdapterIsOff(e);

		} else if (errorMsg.contains("device is not ready")) {
			return deviceNotReady(e);

		} else {
			return unknownError(e);
		}
	}

	private static ForficataException problemLoadingLibraries(BluetoothStateException e) {
		return new ForficataException(e, ""
				+ "Unable to load required libraries for BlueCove.\n"
				+ "\tCheck if these requirements were met: http://www.bluecove.org/bluecove-gpl/index.html\n"
				+ "\tCheck if one of these packages is installed: libbluetooth-dev (Ubuntu), bluez-libs-devel (Fedora), bluez-devel (openSUSE)\n"
				+ "");
	}

	private static ForficataException bluetoothAdapterIsOff(BluetoothStateException e) {
		return new ForficataException(e, ""
				+ "Bluetooth adapter seems to be turned off."
				+ "");
	}

	private static ForficataException deviceNotReady(BluetoothStateException e) {
		return new ForficataException(e, ""
				+ "Bluetooth adapter is not ready. Try turning it off and on again."
				+ "");
	}

	private static ForficataException unknownError(BluetoothStateException e) {
		return new ForficataException(e, ""
				+ "An unknown error occured. Please check the stack trace for details."
				+ "");
	}

}
