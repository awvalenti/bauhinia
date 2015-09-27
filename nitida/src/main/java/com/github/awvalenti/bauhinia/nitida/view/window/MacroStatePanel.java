package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class MacroStatePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public MacroStatePanel() {
		super(new GridLayout(1, 3));
		add(new StateIndication("Idle", Color.RED));
		add(new StateIndication("Preparing", Color.YELLOW));
		add(new StateIndication("Active", Color.GREEN));
	}

}
