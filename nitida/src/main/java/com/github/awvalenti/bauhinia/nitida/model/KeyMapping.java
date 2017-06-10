package com.github.awvalenti.bauhinia.nitida.model;

import static com.github.awvalenti.bauhinia.coronata.WiiRemoteButton.*;
import static java.awt.event.KeyEvent.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.github.awvalenti.bauhinia.coronata.WiiRemoteButton;

public class KeyMapping {

	private final Map<WiiRemoteButton, Integer> mapping = new HashMap<WiiRemoteButton, Integer>();

	public KeyMapping() {
		mapping.put(UP, VK_UP);
		mapping.put(DOWN, VK_DOWN);
		mapping.put(LEFT, VK_LEFT);
		mapping.put(RIGHT, VK_RIGHT);
		mapping.put(A, VK_PAGE_DOWN);
		mapping.put(B, VK_PAGE_UP);
		mapping.put(MINUS, VK_SUBTRACT);
		mapping.put(HOME, VK_HOME);
		mapping.put(PLUS, VK_ADD);
		mapping.put(ONE, VK_ESCAPE);
		mapping.put(TWO, VK_F5);
	}

	public int keycodeFor(WiiRemoteButton button) {
		return mapping.get(button);
	}

	public Collection<Integer> allMappedKeycodes() {
		return mapping.values();
	}

}
