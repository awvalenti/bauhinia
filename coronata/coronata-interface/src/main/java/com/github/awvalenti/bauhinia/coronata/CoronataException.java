package com.github.awvalenti.bauhinia.coronata;

public class CoronataException extends Exception {

	private static final long serialVersionUID = 1L;

	CoronataException(Throwable cause, String detailMessage) {
		super(detailMessage, cause);
	}

}
