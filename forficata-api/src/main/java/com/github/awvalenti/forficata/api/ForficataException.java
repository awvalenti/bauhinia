package com.github.awvalenti.forficata.api;

public class ForficataException extends Exception {

	private static final long serialVersionUID = 1L;

	public ForficataException(Throwable cause, String detailMessage) {
		super(detailMessage, cause);
	}

}
