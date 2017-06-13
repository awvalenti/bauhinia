package com.github.awvalenti.bauhinia.nitida.view;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Properties;

public class Messages {

	private final Properties p = new Properties();

	public Messages() {
		try {
			p.load(getClass().getResourceAsStream("/com/github/awvalenti/bauhinia/nitida/messages.properties"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String get(String key) {
		return p.getProperty(key);
	}

	public String get(String key, Object... arguments) {
		return MessageFormat.format(p.getProperty(key), arguments);
	}

}
