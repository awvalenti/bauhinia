package com.github.awvalenti.bauhinia.nitida.view.common.properties;

public class ProjectProperties {

	private final String projectVersion;

	public ProjectProperties() {
		projectVersion = new PropertiesFileReader("/com/github/awvalenti/bauhinia/nitida/project.properties")
				.get("project.version");
	}

	public String getProjectVersion() {
		return projectVersion;
	}

}
