package com.github.awvalenti.bauhinia.coronata;

public class CoronataFailure extends CoronataException {

	private static final long serialVersionUID = 1L;

	CoronataFailure(Throwable cause, String detailMessage) {
		super(cause, detailMessage);
	}

}
