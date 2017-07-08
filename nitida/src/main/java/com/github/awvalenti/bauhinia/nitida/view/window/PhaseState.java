package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Color;

public enum PhaseState {

	INACTIVE(false, "\u25A1", Color.GRAY),
	RUNNING(  true, "\u22EF", Color.BLACK),
	SUCCESS(  true, "\u2714", Color.GREEN.darker()),
	FAILURE(  true, "\u2718", Color.RED),
	;

	private final boolean active;
	private final String symbol;
	private final Color color;

	private PhaseState(boolean active, String symbol, Color color) {
		this.active = active;
		this.symbol = symbol;
		this.color = color;
	}

	public boolean isActive() {
		return active;
	}

	public String getSymbol() {
		return symbol;
	}

	public Color getColor() {
		return color;
	}

}
