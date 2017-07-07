package com.github.awvalenti.bauhinia.coronata.demo2;

import com.github.awvalenti.bauhinia.coronata.CoronataBuilder;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemote;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataDisconnectionObserver;

public class CoronataDemo2 implements CoronataConnectionObserver,
		CoronataButtonObserver, CoronataDisconnectionObserver {

	public static void main(String[] args) {
		new CoronataDemo2().run();
	}

	public void run() {
		CoronataBuilder.beginConfig()
				.synchronous()	// Because this is a console application
				.oneWiiRemote()
				.onConnection(this)
				.onButton(this)
				.onDisconnection(this)
				.endConfig()
				.run();
	}

	@Override
	public void connected(CoronataWiiRemote wiiRemote) {
		wiiRemote.turnLedOn(0);
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
