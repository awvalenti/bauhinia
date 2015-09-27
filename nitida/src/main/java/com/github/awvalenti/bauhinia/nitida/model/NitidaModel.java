package com.github.awvalenti.bauhinia.nitida.model;

import java.awt.AWTException;
import java.awt.Robot;

import com.github.awvalenti.bauhinia.forficata.ForficataBuilderStep2;
import com.github.awvalenti.bauhinia.forficata.ForficataEventListener;
import com.github.awvalenti.bauhinia.forficata.ForficataFailure;
import com.github.awvalenti.bauhinia.forficata.ForficataPhaseListener;
import com.github.awvalenti.bauhinia.forficata.ForficataWiimoteListener;
import com.github.awvalenti.bauhinia.forficata.Phase;
import com.github.awvalenti.bauhinia.forficata.Wiimote;
import com.github.awvalenti.bauhinia.forficata.WiimoteButton;
import com.github.awvalenti.bauhinia.forficata.WiimoteConnector;

public class NitidaModel implements NitidaInputHandler, ForficataPhaseListener {

	private final Robot robot;
	private final NitidaOutputListener output;
	private final KeyMapping mapping;
	private final WiimoteConnector connector;

	public NitidaModel(ForficataBuilderStep2 builder, NitidaOutputListener output) {
		this(builder, output, new NullForficataEventListener());
	}

	public NitidaModel(ForficataBuilderStep2 builder, ForficataEventListener eventListener) {
		this(builder, new NullNitidaOutputListener(), eventListener);
	}
	public NitidaModel(ForficataBuilderStep2 builder, NitidaOutputListener output,
			ForficataEventListener eventListener) {
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
				.eventListener(eventListener)
				.phaseListener(this)
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

	@Override
	public void running(Phase phase) {
		output.running(phase);
	}

	@Override
	public void success(Phase phase) {
		output.success(phase);
	}

	@Override
	public void failure(Phase phase, ForficataFailure failure) {
		output.failure(phase, failure);
	}

}
