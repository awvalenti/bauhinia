package com.github.awvalenti.bauhinia.coronata.demo4leds;

import com.github.awvalenti.bauhinia.coronata.CoronataBuilder;
import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemote;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataConnectionObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataErrorObserver;

public class LEDs implements CoronataConnectionObserver, CoronataErrorObserver {

	public static void main(String[] args) {
		new LEDs().run();
	}

	public void run() {
		CoronataBuilder.beginConfig()
				.onError(this)
				.onConnection(this)
				.endConfig()
				.start();
	}

	@Override
	public void errorOccurred(CoronataException e) {
		System.err.println(e);
	}

	@Override
	public void connected(CoronataWiiRemote wiiRemote) {
		System.out.println("Connected! Turning LEDs off...");
		pause(2000);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LEDS_NONE);
		pause(500);

		System.out.println("Turning LEDs on...");
		pause(2000);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LEDS_ALL);
		pause(500);

		System.out.println("Now in sequence...");
		pause(2000);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_0);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_1);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_2);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_3);
		pause(500);

		System.out.println("Now dancing!");
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_0 | CoronataWiiRemote.LED_3);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_1 | CoronataWiiRemote.LED_2);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LEDS_NONE);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_1 | CoronataWiiRemote.LED_2);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_0 | CoronataWiiRemote.LED_3);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_0);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_0 | CoronataWiiRemote.LED_1);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_0 | CoronataWiiRemote.LED_1 | CoronataWiiRemote.LED_2);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LEDS_ALL);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_1 | CoronataWiiRemote.LED_2 | CoronataWiiRemote.LED_3);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_2 | CoronataWiiRemote.LED_3);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LED_3);
		pause(500);
		wiiRemote.setLightedLEDs(CoronataWiiRemote.LEDS_NONE);
		pause(500);

		System.out.println("End of the show!");
		pause(1000);
		wiiRemote.disconnect();
	}

	private static void pause(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
		}
	}

}
