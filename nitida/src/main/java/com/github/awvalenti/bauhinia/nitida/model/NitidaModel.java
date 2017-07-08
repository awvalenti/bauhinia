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
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.ProfileChangedObserver;

public class NitidaModel implements ProfileChangedObserver {

	private final Robot robot;
	private final ButtonMapping mapping;
	private Coronata coronata;

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
				// This avoids keys getting stuck
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

	@Override
	public void presentationAppChanged(PresentationApp app) {
		mapping.setPresentationApp(app);
	}

}
