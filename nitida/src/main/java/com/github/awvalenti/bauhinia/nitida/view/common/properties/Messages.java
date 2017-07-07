package com.github.awvalenti.bauhinia.nitida.view.common.properties;

import java.text.MessageFormat;

public class Messages {

	private final MyProperties p;

	public Messages() {
		p = new MyProperties("/com/github/awvalenti/bauhinia/nitida/messages.properties");
	}

	public String get(String key) {
		return p.get(key);
	}

	public String get(String key, Object... arguments) {
		return MessageFormat.format(p.get(key), arguments);
	}

}
