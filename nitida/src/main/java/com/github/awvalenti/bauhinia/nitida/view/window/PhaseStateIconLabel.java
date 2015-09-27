package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;

public class PhaseStateIconLabel extends JLabel {

	private static final long serialVersionUID = 1L;

	public PhaseStateIconLabel() {
		setPreferredSize(new Dimension(32, 32));
		setHorizontalAlignment(CENTER);
		setVerticalAlignment(CENTER);
		setFont(new Font("monospace", Font.PLAIN, 22));
	}

	public final void setState(PhaseState state) {
		setText(state.getSymbol());
		setForeground(state.getColor());
	}

}
