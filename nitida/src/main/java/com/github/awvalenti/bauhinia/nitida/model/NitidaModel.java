package com.github.awvalenti.bauhinia.nitida.model;

import java.awt.AWTException;
import java.awt.Robot;

import com.github.awvalenti.bauhinia.forficata.ForficataBuilderStep2;
import com.github.awvalenti.bauhinia.forficata.ForficataPhaseListener;
import com.github.awvalenti.bauhinia.forficata.ForficataWiimoteListener;
import com.github.awvalenti.bauhinia.forficata.Wiimote;
import com.github.awvalenti.bauhinia.forficata.WiimoteButton;
import com.github.awvalenti.bauhinia.forficata.WiimoteConnector;

public class NitidaModel implements NitidaInputHandler, ForficataPhaseListener {

	private final Robot robot;
	private final NitidaOutputListener output;
	private final KeyMapping mapping;
	private final WiimoteConnector connector;

	public NitidaModel(ForficataBuilderStep2 builder, NitidaOutputListener output) {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
		this.output = output;
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
						NitidaModel.this.output.wiimoteDisconnected();
					}
				})
				.withPhaseListener(this)
				.build();
	}

	@Override
	public void startSearch() {
		connector.start();
	}

	public void wiimoteConnected(final Wiimote wiimote) {
		wiimote.turnLedOn(0);
		output.remoteControlActivated();
	}

}
