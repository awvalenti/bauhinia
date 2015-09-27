package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;

public class MacroStateIndication extends JLabel {

	private static final long serialVersionUID = 1L;

	private final Color originalBackgroundColor;

	public MacroStateIndication(String title, Color color) {
		super(title, new CircleIcon(color), JLabel.LEFT);
		originalBackgroundColor = getBackground();
		setDisabledIcon(new CircleIcon(color.darker().darker()));
		setBorder(BorderFactory.createEtchedBorder());
		setOpaque(true);
		setEnabled(false);
	}

	@Override
	public final void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		setBackground(enabled ? Color.WHITE : originalBackgroundColor);
	}

	private static class CircleIcon implements Icon {

		private final Color color;

		public CircleIcon(Color color) {
			this.color = color;
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
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

}
