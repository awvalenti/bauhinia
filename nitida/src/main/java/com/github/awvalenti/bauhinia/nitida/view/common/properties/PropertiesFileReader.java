package com.github.awvalenti.bauhinia.nitida.view.common.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

public class PropertiesFileReader {

	private final Properties p = new Properties();

	public PropertiesFileReader(String path) {
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Set<String> keySet() {
		// We know p.keySet() is actually a Set<String>, not a Set<Object>.
		// @SuppressWarnings and a cast allow us to return it as such.
		return (Set) p.keySet();
	}

}
