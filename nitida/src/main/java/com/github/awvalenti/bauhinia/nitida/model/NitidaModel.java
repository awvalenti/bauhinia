package com.github.awvalenti.bauhinia.nitida.model;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

import com.github.awvalenti.bauhinia.forficata.api.ForficataAsyncListener;
import com.github.awvalenti.bauhinia.forficata.api.ForficataException;
import com.github.awvalenti.bauhinia.forficata.api.Wiimote;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteButton;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteButtonListener;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnectedCallback;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnector;
import com.github.awvalenti.bauhinia.forficata.factory.crossplatform.ForficataFactoryCrossplatform;

public class NitidaModel {

	private final ForficataAsyncListener listener;

	public NitidaModel(ForficataAsyncListener listener) {
		this.listener = listener;
	}

	public void run() {
		listener.setIdleState();
		WiimoteConnector connector = new ForficataFactoryCrossplatform().createConnector(1);
		try {
			listener.setSearchingState();
			connector.searchAndConnect(new WiimoteConnectedCallback() {
				@Override
				public void wiimoteConnected(final Wiimote wiimote) {
					try {
						wiimote.turnLedOn(0);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
					listener.setConnectedState();

					try {
						final Robot robot = new Robot();
						final KeyMapping mapping = new KeyMapping();
						wiimote.setButtonListener(new WiimoteButtonListener() {
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
								listener.setIdleState();
							}
						});
					} catch (AWTException e) {
						throw new RuntimeException(e);
					}
				}
			});
		} catch (ForficataException e) {
			listener.setIdleState();
			e.printStackTrace();
			System.err.println("\n" + e.getMessage());
		}
	}

}
