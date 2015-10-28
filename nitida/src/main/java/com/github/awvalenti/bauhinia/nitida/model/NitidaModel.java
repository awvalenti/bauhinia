package com.github.awvalenti.bauhinia.nitida.model;

import java.awt.AWTException;
import java.awt.Robot;

import com.github.awvalenti.bauhinia.coronata.CoronataBuilderStep3;
import com.github.awvalenti.bauhinia.coronata.Wiimote;
import com.github.awvalenti.bauhinia.coronata.WiimoteButton;
import com.github.awvalenti.bauhinia.coronata.WiimoteConnector;
import com.github.awvalenti.bauhinia.coronata.listeners.CoronataWiimoteFullListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiimoteConnectionObserver;

public class NitidaModel implements NitidaControllable, CoronataWiimoteConnectionObserver,
		CoronataWiimoteFullListener {

	private final Robot robot;
	private final KeyMapping mapping;
	private WiimoteConnector connector;

	public NitidaModel(CoronataBuilderStep3 builder) {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
		this.mapping = new KeyMapping();
		builder.wiimoteConnectionObserver(this).buttonListener(this);
	}

	public void setConnector(WiimoteConnector connector) {
		this.connector = connector;
	}

	@Override
	public void connect() {
		connector.run();
	}

	@Override
	public void wiimoteConnected(Wiimote wiimote) {
		wiimote.turnLedOn(0);
	}

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
	}

}
