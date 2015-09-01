package com.github.awvalenti.forficata.implementation.wiimotej;

import java.io.IOException;

import wiiusej.WiiUseApiManager;
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

import com.github.awvalenti.forficata.api.Wiimote;
import com.github.awvalenti.forficata.api.WiimoteButton;
import com.github.awvalenti.forficata.api.WiimoteConnectedCallback;
import com.github.awvalenti.forficata.api.WiimoteConnector;
import com.github.awvalenti.forficata.api.WiimoteListener;

public class WiimoteJWiimoteConnector implements WiimoteConnector {

	@Override
	public void searchAndConnect(final WiimoteConnectedCallback callback) {
		wiiusej.Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(1, false);
		if (wiimotes.length > 0) {
			final wiiusej.Wiimote wiiusejWiimote = wiimotes[0];
			callback.wiimoteConnected(new Wiimote() {

				@Override
				public void turnLedOn(int ledIndex) throws IOException {
					wiiusejWiimote.setLeds(ledIndex == 0, ledIndex == 1, ledIndex == 2, ledIndex == 3);
				}

				@Override
				public void stopVibration() throws IOException {
				}

				@Override
				public void startVibration() throws IOException {
				}

				@Override
				public void addListener(final WiimoteListener listener) {
					wiiusejWiimote.addWiiMoteEventListeners(new wiiusej.wiiusejevents.utils.WiimoteListener() {

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

						@Override
						public void onDisconnectionEvent(DisconnectionEvent arg0) {
							listener.wiimoteDisconnected();
						}

						@Override
						public void onClassicControllerRemovedEvent(ClassicControllerRemovedEvent arg0) {
						}

						@Override
						public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent arg0) {
						}

						@Override
						public void onButtonsEvent(WiimoteButtonsEvent buttonsEvent) {
							if (buttonsEvent.isButtonUpJustPressed()) listener.buttonPressed(WiimoteButton.UP);
							if (buttonsEvent.isButtonDownJustPressed()) listener.buttonPressed(WiimoteButton.DOWN);
							if (buttonsEvent.isButtonLeftJustPressed()) listener.buttonPressed(WiimoteButton.LEFT);
							if (buttonsEvent.isButtonRightJustPressed()) listener.buttonPressed(WiimoteButton.RIGHT);
							if (buttonsEvent.isButtonAJustPressed()) listener.buttonPressed(WiimoteButton.A);
							if (buttonsEvent.isButtonBJustPressed()) listener.buttonPressed(WiimoteButton.B);
							if (buttonsEvent.isButtonMinusJustPressed()) listener.buttonPressed(WiimoteButton.MINUS);
							if (buttonsEvent.isButtonHomeJustPressed()) listener.buttonPressed(WiimoteButton.HOME);
							if (buttonsEvent.isButtonPlusJustPressed()) listener.buttonPressed(WiimoteButton.PLUS);
							if (buttonsEvent.isButtonOneJustPressed()) listener.buttonPressed(WiimoteButton.ONE);
							if (buttonsEvent.isButtonTwoJustPressed()) listener.buttonPressed(WiimoteButton.TWO);

							if (buttonsEvent.isButtonUpJustReleased()) listener.buttonReleased(WiimoteButton.UP);
							if (buttonsEvent.isButtonDownJustReleased()) listener.buttonReleased(WiimoteButton.DOWN);
							if (buttonsEvent.isButtonLeftJustReleased()) listener.buttonReleased(WiimoteButton.LEFT);
							if (buttonsEvent.isButtonRightJustReleased()) listener.buttonReleased(WiimoteButton.RIGHT);
							if (buttonsEvent.isButtonAJustReleased()) listener.buttonReleased(WiimoteButton.A);
							if (buttonsEvent.isButtonBJustReleased()) listener.buttonReleased(WiimoteButton.B);
							if (buttonsEvent.isButtonMinusJustReleased()) listener.buttonReleased(WiimoteButton.MINUS);
							if (buttonsEvent.isButtonHomeJustReleased()) listener.buttonReleased(WiimoteButton.HOME);
							if (buttonsEvent.isButtonPlusJustReleased()) listener.buttonReleased(WiimoteButton.PLUS);
							if (buttonsEvent.isButtonOneJustReleased()) listener.buttonReleased(WiimoteButton.ONE);
							if (buttonsEvent.isButtonTwoJustReleased()) listener.buttonReleased(WiimoteButton.TWO);
						}
					});
				}
			});
		}
	}

	@Override
	public void connectToWiimoteAt(String bluetoothAddress, WiimoteConnectedCallback callback) {
	}

}
