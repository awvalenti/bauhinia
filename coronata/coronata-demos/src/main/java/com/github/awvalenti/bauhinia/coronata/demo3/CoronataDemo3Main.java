package com.github.awvalenti.bauhinia.coronata.demo3;

import com.github.awvalenti.bauhinia.coronata.Coronata;
import com.github.awvalenti.bauhinia.coronata.CoronataConnector;

public class CoronataDemo3Main {

	public static void main(String[] args) {
		CoronataDemo3Window window = new CoronataDemo3Window();

		CoronataConnector connector = Coronata.guidedBuilder()
				.asynchronous()	// Because this is a graphical application
				.oneWiiRemote()
				.wiiRemoteConnectionObserver(window)
				.buttonListener(window)
				.connectionStateObserver(window)
				.build();

		window.setConnector(connector);

		window.setVisible(true);
	}

}
