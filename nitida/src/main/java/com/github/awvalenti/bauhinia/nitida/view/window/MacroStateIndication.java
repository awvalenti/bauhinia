package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Color;

import javax.swing.BorderFactory;
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

}
