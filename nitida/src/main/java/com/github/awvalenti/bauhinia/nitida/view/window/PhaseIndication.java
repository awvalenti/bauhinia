package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PhaseIndication extends JPanel {

	private static final long serialVersionUID = 1L;

	private final Color originalBackgroundColor;

	private final PhaseStateIconLabel phaseStateIconLabel;
	private final JLabel stepNameLabel;

	public PhaseIndication(String stepName) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEtchedBorder());

		stepNameLabel = new JLabel(stepName);
		originalBackgroundColor = stepNameLabel.getBackground();
		phaseStateIconLabel = new PhaseStateIconLabel();
		add(phaseStateIconLabel, BorderLayout.WEST);
		add(stepNameLabel, BorderLayout.CENTER);

		setState(PhaseState.INACTIVE);
	}

	public final void setState(PhaseState s) {
		setBackground(s.isActive() ? Color.WHITE : originalBackgroundColor);
		stepNameLabel.setEnabled(s.isActive());
		phaseStateIconLabel.setState(s);
	}

}
