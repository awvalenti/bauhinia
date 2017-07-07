package com.github.awvalenti.bauhinia.nitida.model;

import java.awt.AWTException;
import java.awt.Robot;

import com.github.awvalenti.bauhinia.coronata.Coronata;
import com.github.awvalenti.bauhinia.coronata.CoronataBuilderStep3;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemote;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;

public class NitidaModel {

	private final Robot robot;
	private final KeyMapping mapping;
	private Coronata coronata;

	public NitidaModel(CoronataBuilderStep3 builder) {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
		this.mapping = new KeyMapping();

		MultipleEventsObserver o = new MultipleEventsObserver();

		builder
				.onConnection(o)
				.onDisconnection(o)
				.onButton(o);
	}

	public void setCoronata(Coronata coronata) {
		this.coronata = coronata;
	}

	public void run() {
		coronata.run();
	}

	private class MultipleEventsObserver
			implements CoronataConnectionObserver, CoronataDisconnectionObserver, CoronataButtonObserver {

		@Override
		public void connected(CoronataWiiRemote wiiRemote) {
			wiiRemote.turnLedOn(0);
		}

		@Override
		public void disconnected() {
			for (Integer keycode : mapping.allMappedKeycodes()) {
				robot.keyRelease(keycode);
			}
		}

		@Override
		public void buttonPressed(CoronataWiiRemoteButton button) {
			robot.keyPress(mapping.keycodeFor(button));
		}

		@Override
		public void buttonReleased(CoronataWiiRemoteButton button) {
			robot.keyRelease(mapping.keycodeFor(button));
		}

	}

}
