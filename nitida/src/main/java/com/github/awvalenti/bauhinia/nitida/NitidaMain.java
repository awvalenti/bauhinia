package com.github.awvalenti.bauhinia.nitida;

public class NitidaMain {

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("--console")) {
			new NitidaConsole(new ProjectProperties()).run();
		} else {
			new NitidaWindow(new ProjectProperties()).run();
		}
	}

}
