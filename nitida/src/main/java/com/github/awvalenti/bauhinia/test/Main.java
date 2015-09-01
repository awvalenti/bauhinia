package com.github.awvalenti.bauhinia.test;

import java.io.IOException;

import com.github.awvalenti.forficata.api.Wiimote;
import com.github.awvalenti.forficata.api.WiimoteButton;
import com.github.awvalenti.forficata.api.WiimoteConnectedCallback;
import com.github.awvalenti.forficata.api.WiimoteListener;
import com.github.awvalenti.forficata.implementation.wiimotej.WiimoteJWiimoteConnector;

public class Main {

	public static void main(String[] args) {
		new WiimoteJWiimoteConnector().searchAndConnect(new WiimoteConnectedCallback() {
			@Override
			public void wiimoteConnected(Wiimote wiimote) {
				try {
					wiimote.turnLedOn(0);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				wiimote.addListener(new WiimoteListener() {
					@Override
					public void buttonPressed(WiimoteButton button) {
						System.out.printf("Pressed %s\n", button);
					}

					@Override
					public void buttonReleased(WiimoteButton button) {
						System.out.printf("Released %s\n", button);
					}

					@Override
					public void wiimoteDisconnected() {
						System.out.println("Disconnected");
					}
				});
			}
		});
	}

}
