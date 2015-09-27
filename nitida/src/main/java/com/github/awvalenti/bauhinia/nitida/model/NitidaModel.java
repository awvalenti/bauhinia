package com.github.awvalenti.bauhinia.nitida.model;

import java.awt.AWTException;
import java.awt.Robot;

import com.github.awvalenti.bauhinia.forficata.ForficataBuilderStep2;
import com.github.awvalenti.bauhinia.forficata.ForficataWiimoteListener;
import com.github.awvalenti.bauhinia.forficata.Wiimote;
import com.github.awvalenti.bauhinia.forficata.WiimoteButton;
import com.github.awvalenti.bauhinia.forficata.WiimoteConnector;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataWiimoteConnectionObserver;

public class NitidaModel implements NitidaInputHandler, ForficataWiimoteConnectionObserver {

	private final Robot robot;
	private final KeyMapping mapping;
	private final WiimoteConnector connector;

	public NitidaModel(ForficataBuilderStep2 builder, ForficataObserver... observers) {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
		this.mapping = new KeyMapping();
		this.connector = builder
				.oneWiimote(new ForficataWiimoteListener() {
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
				})
				.observers(observers)
				.wiimoteConnectionObserver(this)
				.build();
	}

	@Override
	public void startSearch() {
		connector.run();
	}

	@Override
	public void wiimoteConnected(Wiimote wiimote) {
		wiimote.turnLedOn(0);
	}

}
