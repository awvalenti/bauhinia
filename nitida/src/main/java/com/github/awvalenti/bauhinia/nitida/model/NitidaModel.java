package com.github.awvalenti.bauhinia.nitida.model;

import java.awt.AWTException;
import java.awt.Robot;

import com.github.awvalenti.bauhinia.coronata.CoronataBuilderStep3;
import com.github.awvalenti.bauhinia.coronata.WiiRemote;
import com.github.awvalenti.bauhinia.coronata.WiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.CoronataConnector;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiiRemoteConnectionObserver;

public class NitidaModel implements NitidaControllable, CoronataWiiRemoteConnectionObserver,
		WiiRemoteFullListener {

	private final Robot robot;
	private final KeyMapping mapping;
	private CoronataConnector connector;

	public NitidaModel(CoronataBuilderStep3 builder) {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
		this.mapping = new KeyMapping();
		builder.wiiRemoteConnectionObserver(this).buttonListener(this);
	}

	public void setConnector(CoronataConnector connector) {
		this.connector = connector;
	}

	@Override
	public void connect() {
		connector.run();
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		wiiRemote.turnLedOn(0);
	}

	@Override
	public void buttonPressed(WiiRemoteButton button) {
		robot.keyPress(mapping.keycodeFor(button));
	}

	@Override
	public void buttonReleased(WiiRemoteButton button) {
		robot.keyRelease(mapping.keycodeFor(button));
	}

	@Override
	public void wiiRemoteDisconnected() {
	}

}
