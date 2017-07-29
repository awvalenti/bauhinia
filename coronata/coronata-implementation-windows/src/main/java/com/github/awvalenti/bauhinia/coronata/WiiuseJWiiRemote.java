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
		public void onButtonsEvent(WiimoteButtonsEvent wiiusejEvent) {
			// The ugly and only way to do it

			if (wiiusejEvent.isButtonUpJustPressed()) buttonObserver.buttonPressed(UP);
			if (wiiusejEvent.isButtonDownJustPressed()) buttonObserver.buttonPressed(DOWN);
			if (wiiusejEvent.isButtonLeftJustPressed()) buttonObserver.buttonPressed(LEFT);
			if (wiiusejEvent.isButtonRightJustPressed()) buttonObserver.buttonPressed(RIGHT);
			if (wiiusejEvent.isButtonAJustPressed()) buttonObserver.buttonPressed(A);
			if (wiiusejEvent.isButtonBJustPressed()) buttonObserver.buttonPressed(B);
			if (wiiusejEvent.isButtonMinusJustPressed()) buttonObserver.buttonPressed(MINUS);
			if (wiiusejEvent.isButtonHomeJustPressed()) buttonObserver.buttonPressed(HOME);
			if (wiiusejEvent.isButtonPlusJustPressed()) buttonObserver.buttonPressed(PLUS);
			if (wiiusejEvent.isButtonOneJustPressed()) buttonObserver.buttonPressed(ONE);
			if (wiiusejEvent.isButtonTwoJustPressed()) buttonObserver.buttonPressed(TWO);

			if (wiiusejEvent.isButtonUpJustReleased()) buttonObserver.buttonReleased(UP);
			if (wiiusejEvent.isButtonDownJustReleased()) buttonObserver.buttonReleased(DOWN);
			if (wiiusejEvent.isButtonLeftJustReleased()) buttonObserver.buttonReleased(LEFT);
			if (wiiusejEvent.isButtonRightJustReleased()) buttonObserver.buttonReleased(RIGHT);
			if (wiiusejEvent.isButtonAJustReleased()) buttonObserver.buttonReleased(A);
			if (wiiusejEvent.isButtonBJustReleased()) buttonObserver.buttonReleased(B);
			if (wiiusejEvent.isButtonMinusJustReleased()) buttonObserver.buttonReleased(MINUS);
			if (wiiusejEvent.isButtonHomeJustReleased()) buttonObserver.buttonReleased(HOME);
			if (wiiusejEvent.isButtonPlusJustReleased()) buttonObserver.buttonReleased(PLUS);
			if (wiiusejEvent.isButtonOneJustReleased()) buttonObserver.buttonReleased(ONE);
			if (wiiusejEvent.isButtonTwoJustReleased()) buttonObserver.buttonReleased(TWO);
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