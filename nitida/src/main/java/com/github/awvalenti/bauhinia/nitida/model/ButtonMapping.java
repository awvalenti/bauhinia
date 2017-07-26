package com.github.awvalenti.bauhinia.nitida.model;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemoteButton;
import com.github.awvalenti.bauhinia.nitida.view.common.properties.PropertiesFileReader;

public class ButtonMapping {

	private final Map<CoronataWiiRemoteButton, int[]> buttonToKeycodes =
			new HashMap<CoronataWiiRemoteButton, int[]>();

	public ButtonMapping() {
		setPresentationApp(PresentationApp.getDefault());
	}

	public int[] keycodesFor(CoronataWiiRemoteButton button) {
		return buttonToKeycodes.get(button);
	}

	public Collection<Integer> allMappedKeycodes() {
		Collection<Integer> ret = new ArrayList<Integer>();

		for (int[] keycodes : buttonToKeycodes.values()) {
			for (int keycode : keycodes) {
				ret.add(keycode);
			}
		}

		return ret;
	}

	public final void setPresentationApp(PresentationApp app) {
		buttonToKeycodes.clear();

		PropertiesFileReader properties = new PropertiesFileReader(
				"/com/github/awvalenti/bauhinia/nitida/button-mapping/" +
				app.name() + ".properties");

		for (String buttonName : properties.keySet()) {
			String[] keysNames = properties.get(buttonName).toUpperCase().split(" ");
			buttonToKeycodes.put(buttonFor(buttonName.toUpperCase()), keycodesFor(keysNames));
		}
	}

	private CoronataWiiRemoteButton buttonFor(String key) {
		try {
			return CoronataWiiRemoteButton.valueOf(key);
		} catch (Exception e) {
			throw new IllegalArgumentException(key +
					" is not a valid Wii Remote button name", e);
		}
	}

	private int[] keycodesFor(String[] keysNames) {
		if (keysNames.length == 0 || keysNames[0].isEmpty()) {
			return new int[0];
		}

		int[] ret = new int[keysNames.length];
		for (int i = 0; i < ret.length; ++i) {
			ret[i] = findKeycode(keysNames[i]);
		}
		return ret;
	}

	private int findKeycode(String keyEventClassFieldName) {
		try {
			return KeyEvent.class.getField(keyEventClassFieldName).getInt(null);

		} catch (NoSuchFieldException e) {
			throw new IllegalArgumentException(keyEventClassFieldName +
					" is not a valid key name", e);

		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);

		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);

		} catch (SecurityException e) {
			throw new RuntimeException(e);
		}
	}

}
