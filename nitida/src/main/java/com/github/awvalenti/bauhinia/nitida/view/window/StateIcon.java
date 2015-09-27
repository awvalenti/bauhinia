package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.Icon;

public class StateIcon implements Icon {

	private final Color color;

	public StateIcon(Color color) {
		this.color = color;
	}

	@Override
	public void paintIcon(Component c, Graphics g, int x, int y) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.BLACK);
		g2d.fillRect(6, 6, 24, 24);
		g2d.setColor(color);
		g2d.fillOval(10, 10, 16, 16);
	}

	@Override
	public int getIconWidth() {
		return 28;
	}

	@Override
	public int getIconHeight() {
		return 32;
	}

}
