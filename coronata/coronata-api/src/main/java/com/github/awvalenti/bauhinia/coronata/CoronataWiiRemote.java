package com.github.awvalenti.bauhinia.coronata;

public interface CoronataWiiRemote {

	int LED_0 = 0x10, LED_1 = 0x20, LED_2 = 0x40, LED_3 = 0x80;
	int LEDS_NONE = 0x00, LEDS_ALL = LED_0 | LED_1 | LED_2 | LED_3;

	void setLightedLEDs(int ledsState);

	void startVibration();

	void stopVibration();

}
