package com.github.awvalenti.bauhinia.nitida.model;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

import com.github.awvalenti.bauhinia.forficata.api.ForficataCallback;
import com.github.awvalenti.bauhinia.forficata.api.ForficataException;
import com.github.awvalenti.bauhinia.forficata.api.Wiimote;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteButton;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteButtonListener;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnector;

public class NitidaModel implements ForficataCallback {

	private final NitidaOutput output;
	private final Robot robot;
	private final KeyMapping mapping;
	private NitidaState state;
	private WiimoteConnector connector;

	public NitidaModel(NitidaOutput output, WiimoteConnector connector) {
		try {
			this.output = output;
			this.connector = connector;
			robot = new Robot();
			mapping = new KeyMapping();
			state = NitidaState.IDLE;
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}

	public void run() {
		connector.run(this);
	}

	@Override
	public void searchStarted() {
		state = NitidaState.SEARCHING;
		output.searching();
	}

	@Override
	public void bluetoothDeviceFound(String bluetoothAddress, String deviceClass) {
		output.bluetoothDeviceFound(bluetoothAddress, deviceClass);
	}

	@Override
	public void wiimoteFound() {
		output.identifying();
	}

	@Override
	public void notWiimote() {
		output.notWiimote();
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
				output.idle();
			}
		});

		state = NitidaState.ACTIVE;
		output.active();
	}

	@Override
	public void searchFinished() {
		if (state != NitidaState.ACTIVE) {
			state = NitidaState.IDLE;
			output.idle();
		}
	}

	@Override
	public void errorOccurred(ForficataException e) {
		output.idle();
		e.printStackTrace();
		System.err.println("\n" + e.getMessage());
	}

}
