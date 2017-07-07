package com.github.awvalenti.bauhinia.coronata.demo1;

import com.github.awvalenti.bauhinia.coronata.CoronataBuilder;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemote;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;

public class CoronataDemo1 {

	public static void main(String[] args) {
		CoronataBuilder.beginConfig()
				.synchronous()	// Because this is a console application
				.oneWiiRemote()
				.onConnection(new CoronataConnectionObserver() {
					@Override
					public void connected(CoronataWiiRemote wiiRemote) {
						wiiRemote.turnLedOn(0);
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
