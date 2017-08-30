package com.github.awvalenti.bauhinia.coronata.demo5vibration;

import com.github.awvalenti.bauhinia.coronata.CoronataBuilder;
import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemote;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemoteButton;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataButtonObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataErrorObserver;

public class Vibration implements CoronataConnectionObserver,
		CoronataButtonObserver, CoronataErrorObserver {

	private CoronataWiiRemote wiiRemote;

	public static void main(String[] args) {
		new Vibration().run();
	}

	public void run() {
		CoronataBuilder.beginConfig()
				.oneWiiRemote()
				.onError(this)
				.onConnection(this)
				.onButton(this)
				.endConfig()
				.run();
	}

	@Override
	public void errorOccurred(CoronataException e) {
		System.err.println(e);
	}

	@Override
	public void connected(CoronataWiiRemote wiiRemote) {
		this.wiiRemote = wiiRemote;

		System.out.println("Connected!");
		wiiRemote.startVibration();
		pause(200);
		wiiRemote.stopVibration();

		System.out.println("Press A to start vibration, press B to stop it");
	}

	@Override
	public void buttonPressed(CoronataWiiRemoteButton button) {
		if (button == CoronataWiiRemoteButton.A) {
			wiiRemote.startVibration();
		} else if (button == CoronataWiiRemoteButton.B) {
			wiiRemote.stopVibration();
		}
	}

	@Override
	public void buttonReleased(CoronataWiiRemoteButton button) {
		// TODO Maybe should not require this method to be implemented
		// (see https://github.com/awvalenti/bauhinia/issues/42)
	}

	private static void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

}
