package com.github.awvalenti.bauhinia.nitida.model;

public enum PresentationApp {

	GOOGLE_SLIDES("Google Slides"),

	POWERPOINT_OR_LIBREOFFICE("PowerPoint / LibreOffice"),

	SPEAKER_DECK("Speaker Deck"),

	;

	private final String formattedName;

	private PresentationApp(String formattedName) {
		this.formattedName = formattedName;
	}

	@Override
	public String toString() {
		return formattedName;
	}

	public static PresentationApp getDefault() {
		return POWERPOINT_OR_LIBREOFFICE;
	}

}
