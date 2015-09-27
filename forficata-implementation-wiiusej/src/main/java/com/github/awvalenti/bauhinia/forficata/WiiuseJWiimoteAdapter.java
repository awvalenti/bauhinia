package com.github.awvalenti.bauhinia.forficata;

import static com.github.awvalenti.bauhinia.forficata.WiimoteButton.*;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;

class WiiuseJWiimoteAdapter implements Wiimote {

	private final wiiusej.Wiimote wiiusejWiimote;

	public WiiuseJWiimoteAdapter(wiiusej.Wiimote wiiusejWiimote, ForficataWiimoteListener listener) {
		this.wiiusejWiimote = wiiusejWiimote;
		wiiusejWiimote.addWiiMoteEventListeners(new WiiuseJEventListener(listener));
	}

	@Override
	public void turnLedOn(int ledIndex) {
		ledIndex %= 4;
		wiiusejWiimote.setLeds(ledIndex == 0, ledIndex == 1, ledIndex == 2, ledIndex == 3);
	}

	@Override
	public void startVibration() {
		wiiusejWiimote.activateRumble();
	}

	@Override
	public void stopVibration() {
		wiiusejWiimote.deactivateRumble();
	}

	private static class WiiuseJEventListener implements wiiusej.wiiusejevents.utils.WiimoteListener {

		private final ForficataWiimoteListener listener;

		private WiiuseJEventListener(ForficataWiimoteListener listener) {
			this.listener = listener;
		}

		@Override
		public void onDisconnectionEvent(DisconnectionEvent arg0) {
			listener.wiimoteDisconnected();
		}

		@Override
		public void onButtonsEvent(WiimoteButtonsEvent wiiusejEvent) {
			// We don't have many options here, except to check every button separately

			if (wiiusejEvent.isButtonUpJustPressed()) listener.buttonPressed(UP);
			if (wiiusejEvent.isButtonDownJustPressed()) listener.buttonPressed(DOWN);
			if (wiiusejEvent.isButtonLeftJustPressed()) listener.buttonPressed(LEFT);
			if (wiiusejEvent.isButtonRightJustPressed()) listener.buttonPressed(RIGHT);
			if (wiiusejEvent.isButtonAJustPressed()) listener.buttonPressed(A);
			if (wiiusejEvent.isButtonBJustPressed()) listener.buttonPressed(B);
			if (wiiusejEvent.isButtonMinusJustPressed()) listener.buttonPressed(MINUS);
			if (wiiusejEvent.isButtonHomeJustPressed()) listener.buttonPressed(HOME);
			if (wiiusejEvent.isButtonPlusJustPressed()) listener.buttonPressed(PLUS);
			if (wiiusejEvent.isButtonOneJustPressed()) listener.buttonPressed(ONE);
			if (wiiusejEvent.isButtonTwoJustPressed()) listener.buttonPressed(TWO);

			if (wiiusejEvent.isButtonUpJustReleased()) listener.buttonReleased(UP);
			if (wiiusejEvent.isButtonDownJustReleased()) listener.buttonReleased(DOWN);
			if (wiiusejEvent.isButtonLeftJustReleased()) listener.buttonReleased(LEFT);
			if (wiiusejEvent.isButtonRightJustReleased()) listener.buttonReleased(RIGHT);
			if (wiiusejEvent.isButtonAJustReleased()) listener.buttonReleased(A);
			if (wiiusejEvent.isButtonBJustReleased()) listener.buttonReleased(B);
			if (wiiusejEvent.isButtonMinusJustReleased()) listener.buttonReleased(MINUS);
			if (wiiusejEvent.isButtonHomeJustReleased()) listener.buttonReleased(HOME);
			if (wiiusejEvent.isButtonPlusJustReleased()) listener.buttonReleased(PLUS);
			if (wiiusejEvent.isButtonOneJustReleased()) listener.buttonReleased(ONE);
			if (wiiusejEvent.isButtonTwoJustReleased()) listener.buttonReleased(TWO);
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

}