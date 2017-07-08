package com.github.awvalenti.bauhinia.nitida.other;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ProjectProperties {

	private final String projectVersion;

	public ProjectProperties() {
		try {
			InputStream resourceInputStream = getClass().getResourceAsStream(
					"/com/github/awvalenti/bauhinia/nitida/project.properties");
			Properties properties = new Properties();
			properties.load(resourceInputStream);

			projectVersion = properties.getProperty("project.version");

			resourceInputStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public String getProjectVersion() {
		return projectVersion;
	}

}
