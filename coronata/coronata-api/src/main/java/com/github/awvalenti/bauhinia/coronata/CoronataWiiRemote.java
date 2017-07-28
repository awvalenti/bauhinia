package com.github.awvalenti.bauhinia.coronata;

public interface CoronataWiiRemote {

	int LED_0 = 0x01, LED_1 = 0x02, LED_2 = 0x04, LED_3 = 0x08,
			LED_NONE = 0x00, LED_ALL = LED_0 | LED_1 | LED_2 | LED_3;

	void setLightedLEDs(int ledsState);

	void startVibration();

	void stopVibration();

}
