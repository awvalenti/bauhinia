package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.buttons;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class HyperlinkButton extends JButton {

	private static final long serialVersionUID = 1L;

	public HyperlinkButton(String text, ActionListener actionListener) {
		setText("<html><u>" + text + "</u></html>");
		setBorderPainted(false);
		setOpaque(false);
		setBackground(Color.WHITE);
		setForeground(Color.BLUE);
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		addActionListener(actionListener);
	}

}
