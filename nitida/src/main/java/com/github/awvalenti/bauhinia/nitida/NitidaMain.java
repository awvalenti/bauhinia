package com.github.awvalenti.bauhinia.nitida;

import com.github.awvalenti.bauhinia.nitida.model.NitidaModel;
import com.github.awvalenti.bauhinia.nitida.model.NitidaOutput;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.console.NitidaConsole;
import com.github.awvalenti.bauhinia.nitida.view.window.NitidaWindow;

public class NitidaMain {

	public static void main(String[] args) {
		ProjectProperties projectProperties = new ProjectProperties();

		NitidaOutput view;
		if (args.length > 0 && args[0].equals("--console")) {
			view = new NitidaConsole(projectProperties);
		} else {
			view = new NitidaWindow(projectProperties);
		}
		view.run();

		new NitidaModel(view).run();
	}

}
