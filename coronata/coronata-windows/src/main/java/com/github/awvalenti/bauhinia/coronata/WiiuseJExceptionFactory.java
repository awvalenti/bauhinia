package com.github.awvalenti.bauhinia.coronata;

class WiiuseJExceptionFactory {

	CoronataException errorLoadingNativeLibraries(Throwable cause) {
		return new CoronataException(cause, ""
				+ "Error loading library: " + cause
				+ "");
	}

}
