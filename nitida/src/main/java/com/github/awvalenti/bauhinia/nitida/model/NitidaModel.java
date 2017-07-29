package com.github.awvalenti.bauhinia.nitida.model;

import java.awt.AWTException;
import java.awt.Robot;

import com.github.awvalenti.bauhinia.coronata.Coronata;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemote;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.buildersteps.CoronataBuilderStep3;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;

public class NitidaModel {

	private final Robot robot;
	private final ButtonMapping mapping;
	private Coronata coronata;
	private CoronataWiiRemote wiiRemote;

	public NitidaModel(CoronataBuilderStep3 builder) {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			throw new RuntimeException(e);
		}
		this.mapping = new ButtonMapping();

		MultipleEventsObserver o = new MultipleEventsObserver();

		builder
				.onConnection(o)
				.onDisconnection(o)
				.onButton(o);
	}

	public void setCoronata(Coronata coronata) {
		this.coronata = coronata;
	}

	public void start() {
		coronata.run();
	}

	public void profileChanged(Profile profile) {
		mapping.setProfile(profile);
	}

	public void stop() {
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_3);
		wiiRemote.disconnect();
	}

	private class MultipleEventsObserver
			implements CoronataConnectionObserver, CoronataDisconnectionObserver, CoronataButtonObserver {

		@Override
		public void connected(final CoronataWiiRemote wiiRemote) {
			NitidaModel.this.wiiRemote = wiiRemote;
			wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_0);
		}

		@Override
		public void disconnected() {
			for (Integer keycode : mapping.allMappedKeycodes()) {
				// This avoids keys getting stuck pressed
				// when controller is disconnected
				robot.keyRelease(keycode);
			}
		}

		@Override
		public void buttonPressed(CoronataWiiRemoteButton button) {
			for (int keycode : mapping.keycodesFor(button)) {
				robot.keyPress(keycode);
			}
		}

		@Override
		public void buttonReleased(CoronataWiiRemoteButton button) {
			for (int keycode : mapping.keycodesFor(button)) {
				robot.keyRelease(keycode);
			}
		}

	}

}
