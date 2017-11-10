package com.github.awvalenti.bauhinia.nitida;

import com.github.awvalenti.bauhinia.nitida.view.console.NitidaConsoleMVC;
import com.github.awvalenti.bauhinia.nitida.view.window.NitidaWindowMVC;

public class NitidaMain {

	public static void main(String[] args) {
		if (args.length == 0) {
			new NitidaWindowMVC();

		} else if (args[0].equals("--console")) {
			new NitidaConsoleMVC();

		} else {
			throw new IllegalArgumentException(
					"Unrecognized argument: " + args[0]);
		}
	}

}
