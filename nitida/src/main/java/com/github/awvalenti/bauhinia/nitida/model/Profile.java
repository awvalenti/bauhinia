package com.github.awvalenti.bauhinia.nitida.model;

public enum Profile {

	ADOBE_ACROBAT_READER("Adobe Acrobat Reader"),

	CHROME_FIREFOX_FOXIT_PDF("Chrome / Firefox / Foxit (PDF)"),

	EVINCE("Evince"),

	GOOGLE_SLIDES("Google Slides"),

	POWERPOINT_OR_LIBREOFFICE("PowerPoint / LibreOffice"),

	SLIDESHARE_OR_SPEAKER_DECK("SlideShare / Speaker Deck"),

	;

	private final String formattedName;

	private Profile(String formattedName) {
		this.formattedName = formattedName;
	}

	@Override
	public String toString() {
		return formattedName;
	}

	public static Profile getDefault() {
		return POWERPOINT_OR_LIBREOFFICE;
	}

}
