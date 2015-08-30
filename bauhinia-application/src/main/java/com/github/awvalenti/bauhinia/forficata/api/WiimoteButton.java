package com.github.awvalenti.bauhinia.forficata.api;

public enum WiimoteButton {

	UP   (2, 0x08),
	DOWN (2, 0x04),
	LEFT (2, 0x01),
	RIGHT(2, 0x02),
	A    (3, 0x08),
	B    (3, 0x04),
	MINUS(3, 0x10),
	HOME (3, 0x80),
	PLUS (2, 0x10),
	ONE  (3, 0x02),
	TWO  (3, 0x01),
	;

	private final int byteIndex;
	private final int byteValue;

	private WiimoteButton(int byteIndex, int byteValue) {
		this.byteIndex = byteIndex;
		this.byteValue = byteValue;
	}

	public boolean isPressedAccordingTo(byte[] fourBytes) {
		return (fourBytes[byteIndex] & byteValue) != 0;
	}

}
