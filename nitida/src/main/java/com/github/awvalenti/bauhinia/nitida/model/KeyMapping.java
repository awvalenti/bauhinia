package com.github.awvalenti.bauhinia.nitida.model;

import static com.github.awvalenti.bauhinia.coronata.CoronataWiiRemoteButton.*;
import static java.awt.event.KeyEvent.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemoteButton;

public class KeyMapping {

	private final Map<CoronataWiiRemoteButton, Integer> mapping = new HashMap<CoronataWiiRemoteButton, Integer>();

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
		mapping.put(ONE, VK_F5);
		mapping.put(TWO, VK_ESCAPE);
	}

	public int keycodeFor(CoronataWiiRemoteButton button) {
		return mapping.get(button);
	}

	public Collection<Integer> allMappedKeycodes() {
		return mapping.values();
	}

}
