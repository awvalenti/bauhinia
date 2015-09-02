package com.github.awvalenti.bauhinia.test;

import java.io.IOException;

import com.github.awvalenti.forficata.api.Wiimote;
import com.github.awvalenti.forficata.api.WiimoteButton;
import com.github.awvalenti.forficata.api.WiimoteConnectedCallback;
import com.github.awvalenti.forficata.api.WiimoteConnector;
import com.github.awvalenti.forficata.api.WiimoteListener;
import com.github.awvalenti.forficata.factory.crossplatform.ForficataFactoryCrossplatform;

public class Main {

	public static void main(String[] args) {
		WiimoteConnector connector = new ForficataFactoryCrossplatform().createConnector();
		connector.searchAndConnect(new WiimoteConnectedCallback() {
			@Override
			public void wiimoteConnected(final Wiimote wiimote) {
				try {
					wiimote.turnLedOn(0);
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
				System.out.println("Connected");
				wiimote.addListener(new WiimoteListener() {
					int i;

					@Override
					public void buttonPressed(WiimoteButton button) {
						try {
							wiimote.turnLedOn(++i % 4);
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
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
