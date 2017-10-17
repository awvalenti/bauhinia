package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.CoronataWiiRemoteButton.*;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;

import wiiusej.Wiimote;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;

class WiiuseJWiiRemote implements CoronataWiiRemote {

	private final Wiimote wiimote;

	public WiiuseJWiiRemote(Wiimote wiimote, CoronataButtonObserver buttonObserver,
			CoronataDisconnectionObserver disconnectionObserver) {
		this.wiimote = wiimote;
		wiimote.addWiiMoteEventListeners(
				new WiiuseJEventListener(buttonObserver, disconnectionObserver));
	}

	@Override
	public void setLightedLEDs(int ledsState) {
		wiimote.setLeds(
			(ledsState & LED_0) != 0,
			(ledsState & LED_1) != 0,
			(ledsState & LED_2) != 0,
			(ledsState & LED_3) != 0
		);
	}

	@Override
	public void startVibration() {
		wiimote.activateRumble();
	}

	@Override
	public void stopVibration() {
		wiimote.deactivateRumble();
	}

	private static class WiiuseJEventListener implements WiimoteListener {

		private final CoronataButtonObserver buttonObserver;
		private final CoronataDisconnectionObserver disconnectionObserver;

		private WiiuseJEventListener(CoronataButtonObserver buttonObserver,
				CoronataDisconnectionObserver disconnectionObserver) {
			this.buttonObserver = buttonObserver;
			this.disconnectionObserver = disconnectionObserver;
		}

		@Override
		public void onDisconnectionEvent(DisconnectionEvent e) {
			disconnectionObserver.disconnected();
		}

		@Override
		public void onButtonsEvent(WiimoteButtonsEvent e) {
			final CoronataButtonObserver o = buttonObserver;

			// The ugly and only way to do it

			if (e.isButtonUpJustPressed()) o.buttonPressed(UP);
			else if (e.isButtonUpJustReleased()) o.buttonReleased(UP);

			if (e.isButtonDownJustPressed()) o.buttonPressed(DOWN);
			else if (e.isButtonDownJustReleased()) o.buttonReleased(DOWN);

			if (e.isButtonLeftJustPressed()) o.buttonPressed(LEFT);
			else if (e.isButtonLeftJustReleased()) o.buttonReleased(LEFT);

			if (e.isButtonRightJustPressed()) o.buttonPressed(RIGHT);
			else if (e.isButtonRightJustReleased()) o.buttonReleased(RIGHT);

			if (e.isButtonAJustPressed()) o.buttonPressed(A);
			else if (e.isButtonAJustReleased()) o.buttonReleased(A);

			if (e.isButtonBJustPressed()) o.buttonPressed(B);
			else if (e.isButtonBJustReleased()) o.buttonReleased(B);

			if (e.isButtonMinusJustPressed()) o.buttonPressed(MINUS);
			else if (e.isButtonMinusJustReleased()) o.buttonReleased(MINUS);

			if (e.isButtonHomeJustPressed()) o.buttonPressed(HOME);
			else if (e.isButtonHomeJustReleased()) o.buttonReleased(HOME);

			if (e.isButtonPlusJustPressed()) o.buttonPressed(PLUS);
			else if (e.isButtonPlusJustReleased()) o.buttonReleased(PLUS);

			if (e.isButtonOneJustPressed()) o.buttonPressed(ONE);
			else if (e.isButtonOneJustReleased()) o.buttonReleased(ONE);

			if (e.isButtonTwoJustPressed()) o.buttonPressed(TWO);
			else if (e.isButtonTwoJustReleased()) o.buttonReleased(TWO);
		}

		@Override
		public void onClassicControllerRemovedEvent(ClassicControllerRemovedEvent arg0) {
		}

		@Override
		public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent arg0) {
		}

		@Override
		public void onStatusEvent(StatusEvent arg0) {
		}

		@Override
		public void onNunchukRemovedEvent(NunchukRemovedEvent arg0) {
		}

		@Override
		public void onNunchukInsertedEvent(NunchukInsertedEvent arg0) {
		}

		@Override
		public void onMotionSensingEvent(MotionSensingEvent arg0) {
		}

		@Override
		public void onIrEvent(IREvent arg0) {
		}

		@Override
		public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent arg0) {
		}

		@Override
		public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent arg0) {
		}

		@Override
		public void onExpansionEvent(ExpansionEvent arg0) {
		}

	}

	@Override
	public void disconnect() {
		wiimote.disconnect();

		// Strangely, WiiuseJ does not notify the event automatically.
		// So, we do it manually below.

		for (WiimoteListener l : wiimote.getWiiMoteEventListeners()) {
			l.onDisconnectionEvent(null);
		}
	}

}