package com.github.awvalenti.bauhinia.nitida.model;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

import com.github.awvalenti.bauhinia.forficata.ForficataException;
import com.github.awvalenti.bauhinia.forficata.ForficataListener;
import com.github.awvalenti.bauhinia.forficata.Wiimote;
import com.github.awvalenti.bauhinia.forficata.WiimoteButton;
import com.github.awvalenti.bauhinia.forficata.WiimoteConnector;
import com.github.awvalenti.bauhinia.forficata.WiimoteEventListener;

public class NitidaModel implements NitidaInputHandler, ForficataListener {

	private final WiimoteConnector connector;
	private final NitidaOutput output;
	private final Robot robot;
	private final KeyMapping mapping;
	private NitidaState state;

	public NitidaModel(WiimoteConnector connector, NitidaOutput output) {
		try {
			this.connector = connector;
			this.output = output;
			robot = new Robot();
			mapping = new KeyMapping();
			state = NitidaState.IDLE;
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void startSearch() {
		connector.startSearch(this);
	}

	@Override
	public void searchStarted() {
		state = NitidaState.SEARCHING;
		output.searchStarted();
	}

	@Override
	public void identifyingBluetoothDevice(String bluetoothAddress, String deviceClass) {
		output.identifyingBluetoothDevice(bluetoothAddress, deviceClass);
	}

	@Override
	public void wiimoteFound() {
		output.wiimoteFound();
	}

	@Override
	public void wiimoteConnected(final Wiimote wiimote) {
		try {
			wiimote.turnLedOn(0);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		wiimote.setButtonListener(new WiimoteEventListener() {
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
				state = NitidaState.IDLE;
				output.wiimoteDisconnected();
			}
		});

		state = NitidaState.ACTIVE;
		output.remoteControlActivated();
	}

	@Override
	public void searchFinished() {
		if (state != NitidaState.ACTIVE) {
			state = NitidaState.IDLE;
			output.unableToFindWiimote();
		}
	}

	@Override
	public void errorOccurred(ForficataException e) {
		output.errorOccurred(e);
	}

}
