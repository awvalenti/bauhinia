package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class StateLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	private final Color originalBackgroundColor;

	public StateLabel(String text, Color color) {
		super(text);
		originalBackgroundColor = getBackground();

		setBorder(BorderFactory.createEtchedBorder());
		setIcon(new StateIcon(color));
		setDisabledIcon(new StateIcon(color.darker().darker().darker()));
		setOpaque(true);
		setEnabled(false);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		setBackground(enabled ? Color.WHITE : originalBackgroundColor);
	}

}
