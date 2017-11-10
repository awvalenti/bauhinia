package com.github.awvalenti.bauhinia.coronata;

public enum CoronataPhase {

	LOAD_LIBRARY,

	FIND_WII_REMOTE,

	CONNECT_TO_WII_REMOTE,

	;

	@Override
	public String toString() {
		return name().charAt(0) +
				name().substring(1).replaceAll("_", " ").toLowerCase();
	}
}
