package com.github.awvalenti.bauhinia.coronata.demo1console;

import com.github.awvalenti.bauhinia.coronata.CoronataBuilder;
import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemote;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataErrorObserver;

public class ConsoleWithSeparatedObservers {

	public static void main(String[] args) {
		CoronataBuilder.beginConfig()
				.onError(new CoronataErrorObserver() {
					@Override
					public void errorOccurred(CoronataException e) {
						System.err.println(e);
					}
				})
				.onConnection(new CoronataConnectionObserver() {
					@Override
					public void connected(CoronataWiiRemote wiiRemote) {
						wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_0);
						System.out.println("Connected!");
					}
				})
				.onButton(new CoronataButtonObserver() {
					@Override
					public void buttonPressed(CoronataWiiRemoteButton button) {
						System.out.println(button + " pressed");
					}

					@Override
					public void buttonReleased(CoronataWiiRemoteButton button) {
						System.out.println(button + " released");
					}
				})
				.onDisconnection(new CoronataDisconnectionObserver() {
					@Override
					public void disconnected() {
						System.out.println("Disconnected.");
					}
				})
				.endConfig()
				.run();
	}

}
