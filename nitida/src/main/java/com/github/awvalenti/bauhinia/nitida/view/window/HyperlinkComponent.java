package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class HyperlinkComponent extends JButton {

	private static final long serialVersionUID = 1L;

	public HyperlinkComponent(String text, ActionListener actionListener) {
		setText("<html><u>" + text + "</u></html>");
		setBorderPainted(false);
		setOpaque(false);
		setBackground(Color.WHITE);
		setForeground(Color.BLUE);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addActionListener(actionListener);
	}

}
