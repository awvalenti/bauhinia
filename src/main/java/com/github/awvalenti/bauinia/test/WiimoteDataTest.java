package com.github.awvalenti.bauinia.test;

import java.io.IOException;
import java.util.Arrays;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.L2CAPConnection;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.microedition.io.Connector;

import com.intel.bluetooth.BlueCoveConfigProperties;

public class WiimoteDataTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		configureBlueCove();

		Object lock = new Object();
		startInquiry(new DeviceFoundListener(), lock);
		synchronized (lock) {
			lock.wait();
		}
	}

	private static void configureBlueCove() {
		System.setProperty(BlueCoveConfigProperties.PROPERTY_JSR_82_PSM_MINIMUM_OFF, "true");
	}

	private static class DeviceFoundListener {
		public void deviceFound(RemoteDevice btDevice) {
			try {
				if (deviceIsWiimote(btDevice)) {
					connect(btDevice);
				}
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private static void startInquiry(final DeviceFoundListener listener, final Object lock)
			throws BluetoothStateException {
		LocalDevice.getLocalDevice().getDiscoveryAgent()
				.startInquiry(DiscoveryAgent.GIAC, new DiscoveryListener() {
					public void servicesDiscovered(int transID, ServiceRecord[] servRecord) {
					}

					public void serviceSearchCompleted(int transID, int respCode) {
					}

					public void inquiryCompleted(int discType) {
						lock.notify();
					}

					public void deviceDiscovered(RemoteDevice btDevice, DeviceClass cod) {
						listener.deviceFound(btDevice);
					}
				});
	}

	private static boolean deviceIsWiimote(RemoteDevice btDevice) throws IOException {
		return btDevice.getFriendlyName(false).startsWith("Nintendo");
	}

	private static void connect(RemoteDevice btDevice) throws IOException {
		String baseAddress = "btl2cap://" + btDevice.getBluetoothAddress();
		L2CAPConnection input = (L2CAPConnection) Connector.open(baseAddress + ":13",
				Connector.READ, true);
		L2CAPConnection output = (L2CAPConnection) Connector.open(baseAddress + ":11",
				Connector.WRITE, true);

		turnLedOn(output, 0);

		for (;;) {
			byte[] data = new byte[32];
			input.receive(data);
			if (buttonWasPressed(data)) {
				System.out.println(Arrays.toString(data));
			}
		}
	}

	private static boolean buttonWasPressed(byte[] data) {
		return data[1] == 0x30;
	}

	private static void turnLedOn(L2CAPConnection output, int ledIndex) throws IOException {
		sendDataToWiimote(output, (byte) 0x11, new byte[] { (byte) (1 << (4 + ledIndex)) });
	}

	private static void sendDataToWiimote(L2CAPConnection output, byte commandCode, byte[] data1)
			throws IOException {
		output.send(withTwoBytesAddedBefore(data1, commandCode));
	}

	private static byte[] withTwoBytesAddedBefore(byte[] data1, byte commandCode) {
		byte[] ret = new byte[data1.length + 2];
		ret[0] = 0x52;
		ret[1] = commandCode;
		System.arraycopy(data1, 0, ret, 2, data1.length);
		return ret;
	}

}
