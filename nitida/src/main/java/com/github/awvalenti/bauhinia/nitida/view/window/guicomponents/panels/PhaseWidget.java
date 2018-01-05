package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PhaseWidget extends JPanel {

	private static final long serialVersionUID = 1L;

	private final Color originalBackgroundColor;

	private final PhaseStateIconLabel iconLabel;
	private final JLabel nameLabel;

	public PhaseWidget(String phaseName) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEtchedBorder());

		nameLabel = new JLabel(phaseName);
		originalBackgroundColor = nameLabel.getBackground();
		iconLabel = new PhaseStateIconLabel();
		add(iconLabel, BorderLayout.WEST);
		add(nameLabel, BorderLayout.CENTER);

		setState(PhaseState.INACTIVE);
	}

	public final void setState(PhaseState s) {
		setBackground(s.isActive() ? Color.WHITE : originalBackgroundColor);
		nameLabel.setEnabled(s.isActive());
		iconLabel.setState(s);
	}

	private static class PhaseStateIconLabel extends JLabel {

		private static final long serialVersionUID = 1L;

		public PhaseStateIconLabel() {
			setPreferredSize(new Dimension(32, 32));
			setHorizontalAlignment(CENTER);
			setVerticalAlignment(CENTER);
			setFont(new Font(Font.MONOSPACED, Font.PLAIN, 22));
		}

		public final void setState(PhaseState state) {
			setText(state.getSymbol());
			setForeground(state.getColor());
		}

	}

}
