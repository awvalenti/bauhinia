package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;

public class NitidaWindow {

	private final JFrame frame;

	public NitidaWindow(ProjectProperties projectProperties) {
		frame = new JFrame("nitida " + projectProperties.getProjectVersion());
		frame.setLayout(new BorderLayout());
		frame.setSize(300, 400);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		String[] states = { "Idle", "Connecting", "Active" };
		Color[] colors = { Color.RED, Color.YELLOW, Color.GREEN };

		JPanel state = new JPanel();
		state.setBorder(BorderFactory.createTitledBorder("State"));
		state.setLayout(new GridLayout(states.length, 1));
		for (int i = 0; i < states.length; ++i) {
			final JLabel label = new JLabel(states[i]);
			label.setBorder(BorderFactory.createEtchedBorder());
			label.setIcon(new StateIcon(colors[i]));
			label.setOpaque(true);
			label.setDisabledIcon(new StateIcon(colors[i].darker().darker().darker()));
			label.setEnabled(false);
			state.add(label);
		}
		frame.add(state, BorderLayout.NORTH);

		JPanel log = new JPanel(new BorderLayout());
		log.setBorder(BorderFactory.createTitledBorder("Log"));
		frame.add(log, BorderLayout.CENTER);

		JButton reconnect = new JButton("Reconnect");
		reconnect.setEnabled(false);

		JPanel actions = new JPanel();
		actions.setBorder(BorderFactory.createTitledBorder("Actions"));
		actions.add(reconnect);
		frame.add(actions, BorderLayout.SOUTH);
	}

	public void run() {
		frame.setVisible(true);
	}

	private static class StateIcon implements Icon {
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
			return 32;
		}

		@Override
		public int getIconHeight() {
			return 32;
		}
	}

}
