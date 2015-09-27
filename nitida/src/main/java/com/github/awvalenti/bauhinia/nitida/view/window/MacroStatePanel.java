package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

public class MacroStatePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final Map<MacroState, MacroStateIndication> map = new HashMap<MacroState, MacroStateIndication>();

	public MacroStatePanel(MacroState[] states) {
		super(new GridLayout(1, states.length));
		for (MacroState s : states) {
			MacroStateIndication indication = new MacroStateIndication(s.toString(), s.getColor());
			map.put(s, indication);
			add(indication);
		}
	}

	public void setState(MacroState newState) {
		for (MacroStateIndication indication : map.values()) {
			indication.setEnabled(false);
		}
		map.get(newState).setEnabled(true);
	}

}
