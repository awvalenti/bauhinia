package com.github.awvalenti.bauhinia.nitida;

import static com.github.awvalenti.bauhinia.forficata.api.WiimoteButton.*;
import static java.awt.event.KeyEvent.*;

import java.util.HashMap;
import java.util.Map;

import com.github.awvalenti.bauhinia.forficata.api.WiimoteButton;

public class KeyMapping {

	private final Map<WiimoteButton, Integer> mapping = new HashMap<WiimoteButton, Integer>();

	public KeyMapping() {
		mapping.put(UP, VK_UP);
		mapping.put(DOWN, VK_DOWN);
		mapping.put(LEFT, VK_LEFT);
		mapping.put(RIGHT, VK_RIGHT);
		mapping.put(A, VK_PAGE_DOWN);
		mapping.put(B, VK_PAGE_UP);
		mapping.put(MINUS, VK_MINUS);
		mapping.put(HOME, VK_HOME);
		mapping.put(PLUS, VK_PLUS);
		mapping.put(ONE, VK_ESCAPE);
		mapping.put(TWO, VK_F5);
	}

	public int keycodeFor(WiimoteButton button) {
		return mapping.get(button);
	}

}
