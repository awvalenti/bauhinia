package com.github.awvalenti.bauhinia.coronata.demo2;

import com.github.awvalenti.bauhinia.coronata.Coronata;
import com.github.awvalenti.bauhinia.coronata.WiiRemote;
import com.github.awvalenti.bauhinia.coronata.WiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteButtonListener;
import com.github.awvalenti.bauhinia.coronata.listeners.WiiRemoteDisconnectionListener;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataWiiRemoteConnectionObserver;

public class CoronataDemo2 implements CoronataWiiRemoteConnectionObserver,
		WiiRemoteButtonListener, WiiRemoteDisconnectionListener {

	public static void main(String[] args) {
		new CoronataDemo2().run();
	}

	public void run() {
		Coronata.guidedBuilder()
				.synchronous()	// Because this is a console application
				.oneWiiRemote()
				.wiiRemoteConnectionObserver(this)
				.buttonListener(this)
				.disconnectionListener(this)
				.build()
				.run();
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		wiiRemote.turnLedOn(0);
		System.out.println("Connected!");
	}

	@Override
	public void wiiRemoteDisconnected() {
		System.out.println("Disconnected.");
	}

	@Override
	public void buttonPressed(WiiRemoteButton button) {
		System.out.println(button + " pressed");
	}

	@Override
	public void buttonReleased(WiiRemoteButton button) {
		System.out.println(button + " released");
	}

}
