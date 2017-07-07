package com.github.awvalenti.bauhinia.coronata.demo3;

import com.github.awvalenti.bauhinia.coronata.Coronata;
import com.github.awvalenti.bauhinia.coronata.CoronataBuilder;

public class CoronataDemo3Main {

	public static void main(String[] args) {
		CoronataDemo3Window window = new CoronataDemo3Window();

		Coronata coronata = CoronataBuilder.beginConfig()
				.asynchronous()	// Because this is a graphical application
				.oneWiiRemote()
				.onConnection(window)
				.onButton(window)
				.onLifecycleState(window)
				.onDisconnection(window)
				.endConfig();

		window.setCoronata(coronata);

		window.setVisible(true);
	}

}
