package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;

public class ConnectionStateIndication extends JPanel {

	private static final long serialVersionUID = 1L;

	private final Color originalBgColor;

	private JLabel iconLabel;
	private JLabel textLabel;

	public ConnectionStateIndication(String title, Color enabledColor) {
		setLayout(new BorderLayout());
		setBorder(new CompoundBorder(BorderFactory.createEtchedBorder(),
				BorderFactory.createEmptyBorder(5, 0, 5, 0)));
		setOpaque(true);

		iconLabel = new JLabel(new CircleIcon(enabledColor));
		iconLabel.setDisabledIcon(
				new CircleIcon(enabledColor.darker().darker()));
		iconLabel.setOpaque(true);

		originalBgColor = iconLabel.getBackground();

		textLabel = new JLabel(title, JLabel.CENTER);
		textLabel.setOpaque(true);

		add(iconLabel, BorderLayout.NORTH);
		add(textLabel, BorderLayout.SOUTH);
	}

	@Override
	public final void setEnabled(boolean enabled) {
		iconLabel.setEnabled(enabled);
		textLabel.setEnabled(enabled);
		setBackground(enabled ? Color.WHITE : originalBgColor);
		iconLabel.setBackground(enabled ? Color.WHITE : originalBgColor);
		textLabel.setBackground(enabled ? Color.WHITE : originalBgColor);
	}

	private static class CircleIcon implements Icon {

		private final Color innerCircleColor;

		public CircleIcon(Color innerCircleColor) {
			this.innerCircleColor = innerCircleColor;
		}

		@Override
		public void paintIcon(Component c, Graphics g, int x, int y) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			int startingX = (c.getWidth() - getIconWidth()) / 2,
					outerCircleRadius = 25, innerCircleRadius = 15,
					distanceBetweenCircles =
							(outerCircleRadius - innerCircleRadius) / 2;

			g2d.setColor(Color.BLACK);
			g2d.fillOval(startingX, 0, outerCircleRadius, outerCircleRadius);

			g2d.setColor(innerCircleColor);
			g2d.fillOval(startingX + distanceBetweenCircles,
					distanceBetweenCircles, innerCircleRadius,
					innerCircleRadius);
		}

		@Override
		public int getIconWidth() {
			return 25;
		}

		@Override
		public int getIconHeight() {
			return 28;
		}

	}

}
