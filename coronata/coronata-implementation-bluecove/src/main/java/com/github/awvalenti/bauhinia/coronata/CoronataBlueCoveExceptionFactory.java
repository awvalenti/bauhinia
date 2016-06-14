package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;

import com.github.awvalenti.bauhinia.coronata.CoronataException;

class CoronataBlueCoveExceptionFactory {

	public CoronataException wiiRemoteRejectedConnection(IOException cause) {
		return new CoronataException(cause, ""
				+ "Connection refused. Details: " + cause
				+ "");
	}

	public CoronataException correspondingTo(BluetoothStateException cause) {
		String errorMsg = cause.getMessage().toLowerCase();

		if (errorMsg.contains("librar") && errorMsg.contains("not available")) {
			return problemLoadingLibraries(cause);

		} else if (errorMsg.contains("device is not available") || errorMsg.contains("stack not detected")) {
			return bluetoothAdapterIsOff(cause);

		} else if (errorMsg.contains("device is not ready")) {
			return deviceNotReady(cause);

		} else {
			return unknownError(cause);
		}
	}

	private CoronataException problemLoadingLibraries(BluetoothStateException cause) {
		return new CoronataException(cause, ""
				+ "Unable to load required libraries for BlueCove.\n"
				+ "\tCheck if the requirements described here were met:"
				+ " http://www.bluecove.org/bluecove-gpl/index.html\n"
				+ "\tTry installing one of these packages:"
				+ " libbluetooth-dev (Ubuntu), bluez-libs-devel (Fedora), bluez-devel (openSUSE)\n"
				+ "");
	}

	private CoronataException bluetoothAdapterIsOff(BluetoothStateException cause) {
		return new CoronataException(cause, ""
				+ "Bluetooth adapter is unavailable. Check if it is present and turned on."
				+ "");
	}

	private CoronataException deviceNotReady(BluetoothStateException cause) {
		return new CoronataException(cause, ""
				+ "Bluetooth adapter is not ready. Try turning it off and on again."
				+ "");
	}

	private CoronataException unknownError(Exception cause) {
		return new CoronataException(cause, ""
				+ "An unknown error occured. Please check the stack trace for details."
				+ "");
	}

}
