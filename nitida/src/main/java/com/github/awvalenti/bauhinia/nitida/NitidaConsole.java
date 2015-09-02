package com.github.awvalenti.bauhinia.nitida;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.IOException;

import com.github.awvalenti.bauhinia.forficata.api.ForficataException;
import com.github.awvalenti.bauhinia.forficata.api.Wiimote;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteButton;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteButtonListener;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnectedCallback;
import com.github.awvalenti.bauhinia.forficata.api.WiimoteConnector;
import com.github.awvalenti.bauhinia.forficata.factory.crossplatform.ForficataFactoryCrossplatform;

public class NitidaConsole {

	public static void main(String[] args) {
		WiimoteConnector connector = new ForficataFactoryCrossplatform().createConnector(1);
		try {
			System.out.println("Searching for Wiimote...");
			connector.searchAndConnect(new WiimoteConnectedCallback() {
				@Override
				public void wiimoteConnected(final Wiimote wiimote) {
					try {
						wiimote.turnLedOn(0);
					} catch (IOException e) {
						throw new RuntimeException(e);
					}
					System.out.println("Connected to Wiimote!");

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
								System.out.println("Wiimote disconnected");
							}
						});
					} catch (AWTException e) {
						throw new RuntimeException(e);
					}
				}
			});
		} catch (ForficataException e) {
			e.printStackTrace();
			System.err.println("\n" + e);
		}
	}

}
