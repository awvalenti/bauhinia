package com.github.awvalenti.bauhinia.nitida.model;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

import com.github.awvalenti.bauhinia.forficata.api.ForficataCallback;
import com.github.awvalenti.bauhinia.forficata.api.ForficataException;
import com.github.awvalenti.bauhinia.forficata.api.Wiimote;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteButton;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteButtonListener;
import com.github.awvalenti.bauhinia.forficata.factory.crossplatform.Forficata;

public class NitidaModel implements ForficataCallback {

	private final NitidaOutput listener;
	private final Robot robot;
	private final KeyMapping mapping;

	public NitidaModel(NitidaOutput listener) {
		try {
			this.robot = new Robot();
			this.mapping = new KeyMapping();
			this.listener = listener;
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}

	public void run() {
		Forficata.asyncConnector().run(this);
	}

	@Override
	public void searchStarted() {
		listener.enteredSearchingStarted();
	}

	@Override
	public void bluetoothDeviceFound(String bluetoothAddress, String deviceClass) {
		listener.bluetoothDeviceFound(bluetoothAddress, deviceClass);
	}

	@Override
	public void wiimoteConnected(final Wiimote wiimote) {
		try {
			wiimote.turnLedOn(0);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		wiimote.setButtonListener(new WiimoteButtonListener() {
			@Override
			public void buttonPressed(WiimoteButton button) {
				robot.keyPress(mapping.keycodeFor(button));
			}

			@Override
			public void buttonReleased(WiimoteButton button) {
				robot.keyRelease(mapping.keycodeFor(button));
			}

			@Override
			public void wiimoteDisconnected() {
				listener.enteredIdleState();
			}
		});

		listener.enteredActiveState();
	}

	@Override
	public void errorOccurred(ForficataException e) {
		listener.enteredIdleState();
		e.printStackTrace();
		System.err.println("\n" + e.getMessage());
	}

}
