package com.github.awvalenti.bauhinia.nitida;

import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.console.NitidaConsole;
import com.github.awvalenti.bauhinia.nitida.view.window.NitidaWindow;

public class NitidaMain {

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("--console")) {
			new NitidaConsole(new ProjectProperties()).run();
		} else {
			new NitidaWindow(new ProjectProperties()).run();
		}
	}

}
