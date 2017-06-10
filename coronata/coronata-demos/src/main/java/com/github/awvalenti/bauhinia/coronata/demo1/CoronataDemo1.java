package com.github.awvalenti.bauhinia.coronata.demo1;

import com.github.awvalenti.bauhinia.coronata.Coronata;
import com.github.awvalenti.bauhinia.coronata.WiiRemote;
import com.github.awvalenti.bauhinia.coronata.WiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteButtonListener;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteDisconnectionListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiiRemoteConnectionObserver;

public class CoronataDemo1 {

	public static void main(String[] args) {
		Coronata.guidedBuilder()
				.synchronous()	// Because this is a console application
				.oneWiiRemote()
				.wiiRemoteConnectionObserver(new CoronataWiiRemoteConnectionObserver() {
					@Override
					public void wiiRemoteConnected(WiiRemote wiiRemote) {
						wiiRemote.turnLedOn(0);
						System.out.println("Connected!");
					}
				})
				.buttonListener(new WiiRemoteButtonListener() {
					@Override
					public void buttonPressed(WiiRemoteButton button) {
						System.out.println(button + " pressed");
					}

					@Override
					public void buttonReleased(WiiRemoteButton button) {
						System.out.println(button + " released");
					}
				})
				.disconnectionListener(new WiiRemoteDisconnectionListener() {
					@Override
					public void wiiRemoteDisconnected() {
						System.out.println("Disconnected.");
					}
				})
				.build()
				.run();
	}

}
