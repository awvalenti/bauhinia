package com.github.awvalenti.bauhinia.coronata;

interface WiiRemoteConstants {

	// Source: http://wiibrew.org/wiki/Wiimote

	byte
			SET_REPORT = 0x52,

			ID_LEDS_VIBRATION = 0x11,

			LEDS_MASK = (byte) 0xF0,

			VIBRATION_MASK = 0x01;

}
