package com.github.awvalenti.bauhinia.coronata;

public enum Phase {

	LOAD_LIBRARIES,

	FIND_WIIMOTE,

	CONNECT_TO_WIIMOTE,

	;

	@Override
	public String toString() {
		return name().charAt(0) + name().substring(1).replaceAll("_", " ").toLowerCase();
	}
}
