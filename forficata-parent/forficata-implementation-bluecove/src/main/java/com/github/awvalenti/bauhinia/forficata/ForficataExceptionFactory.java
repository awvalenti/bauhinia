package com.github.awvalenti.bauhinia.forficata;

import java.io.IOException;

import javax.bluetooth.BluetoothStateException;

import com.github.awvalenti.bauhinia.forficata.ForficataException;

class ForficataExceptionFactory {

	public static ForficataException unknownError(Exception e) {
		return new ForficataException(e, ""
				+ "An unknown error occured. Please check the stack trace for details."
				+ "");
	}

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
				+ "\tCheck if the requirements described here were met:"
				+ " http://www.bluecove.org/bluecove-gpl/index.html\n"
				+ "\tTry installing one of these packages:"
				+ " libbluetooth-dev (Ubuntu), bluez-libs-devel (Fedora), bluez-devel (openSUSE)\n"
				+ "");
	}

	private static ForficataException bluetoothAdapterIsOff(BluetoothStateException e) {
		return new ForficataException(e, ""
				+ "Bluetooth adapter is unavailable. Check if it is present and turned on."
				+ "");
	}

	private static ForficataException deviceNotReady(BluetoothStateException e) {
		return new ForficataException(e, ""
				+ "Bluetooth adapter is not ready. Try turning it off and on again."
				+ "");
	}

	public static ForficataException deviceRejectedIdentification(IOException e) {
		return connectionRefused(e);
	}

	public static ForficataException wiimoteRejectedConnection(IOException e) {
		return connectionRefused(e);
	}

	private static ForficataException connectionRefused(IOException e) {
		return new ForficataException(e, ""
				+ "Connection refused. Details: " + e
				+ "");
	}

}
