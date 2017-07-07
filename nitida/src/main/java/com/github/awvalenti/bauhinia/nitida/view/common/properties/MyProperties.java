package com.github.awvalenti.bauhinia.nitida.view.common.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperties {

	private final Properties p = new Properties();

	public MyProperties(String path) {
		InputStream stream = getClass().getResourceAsStream(path);

		if (stream == null) {
			throw new IllegalArgumentException("Path not found: " + path);
		}

		try {
			p.load(stream);

		} catch (IOException e) {
			throw new RuntimeException(e);

		} finally {
			try {
				stream.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public String get(String key) {
		return p.getProperty(key);
	}

}
