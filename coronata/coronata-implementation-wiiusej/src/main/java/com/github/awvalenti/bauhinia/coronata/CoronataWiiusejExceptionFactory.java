package com.github.awvalenti.bauhinia.coronata;

class CoronataWiiusejExceptionFactory {

	public CoronataException errorLoadingNativeLibraries(Throwable cause) {
		return new CoronataException(cause, ""
				+ "Error loading native libraries: "
				+ "");
	}

}
