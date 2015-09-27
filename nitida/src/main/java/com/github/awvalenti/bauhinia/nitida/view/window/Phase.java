package com.github.awvalenti.bauhinia.nitida.view.window;

public enum Phase {

	LOAD_LIBRARIES,

	FIND_WIIMOTE,

	IDENTIFY_WIIMOTE,

	CONNECT_TO_WIIMOTE,

	;

	@Override
	public String toString() {
		return name().charAt(0) + name().substring(1).toLowerCase().replace('_', ' ');
	}
}
