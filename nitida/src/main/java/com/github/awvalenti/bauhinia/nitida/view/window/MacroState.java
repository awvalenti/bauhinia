package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Color;

public enum MacroState {

	IDLE(Color.RED),

	PREPARING(Color.YELLOW),

	ACTIVE(Color.GREEN),

	;

	private final Color color;

	private MacroState(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return name().charAt(0) + name().substring(1).toLowerCase();
	}

	public Color getColor() {
		return color;
	}

}
