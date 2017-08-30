package com.github.awvalenti.bauhinia.coronata.demo2console;

import com.github.awvalenti.bauhinia.coronata.CoronataBuilder;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemote;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;

public class ConsoleWithIntegratedObserver implements CoronataConnectionObserver,
		CoronataButtonObserver, CoronataDisconnectionObserver {

	public static void main(String[] args) {
		new ConsoleWithIntegratedObserver().run();
	}

	public void run() {
		CoronataBuilder.beginConfig()
				.oneWiiRemote()
				.onConnection(this)
				.onButton(this)
				.onDisconnection(this)
				.endConfig()
				.run();
	}

	@Override
	public void connected(CoronataWiiRemote wiiRemote) {
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_0);
		System.out.println("Connected!");
	}

	@Override
	public void disconnected() {
		System.out.println("Disconnected.");
	}

	@Override
	public void buttonPressed(CoronataWiiRemoteButton button) {
		System.out.println(button + " pressed");
	}

	@Override
	public void buttonReleased(CoronataWiiRemoteButton button) {
		System.out.println(button + " released");
	}

}
