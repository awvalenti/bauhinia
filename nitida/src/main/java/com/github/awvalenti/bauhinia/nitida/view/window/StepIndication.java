package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StepIndication extends JPanel {

	private static final long serialVersionUID = 1L;

	private final Color originalBackgroundColor;

	private final StepIconLabel stepIconLabel;
	private final JLabel stepNameLabel;

	public StepIndication(String stepName) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEtchedBorder());

		stepNameLabel = new JLabel(stepName);
		originalBackgroundColor = stepNameLabel.getBackground();
		stepIconLabel = new StepIconLabel();
		add(stepIconLabel, BorderLayout.WEST);
		add(stepNameLabel, BorderLayout.CENTER);

		setState(StepState.INACTIVE);
	}

	public final void setState(StepState s) {
		setBackground(s.isActive() ? Color.WHITE : originalBackgroundColor);
		stepNameLabel.setEnabled(s.isActive());
		stepIconLabel.setState(s);
	}

}
