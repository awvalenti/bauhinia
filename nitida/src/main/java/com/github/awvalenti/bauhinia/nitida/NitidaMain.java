package com.github.awvalenti.bauhinia.nitida;

import com.github.awvalenti.bauhinia.forficata.Forficata;
import com.github.awvalenti.bauhinia.nitida.model.NitidaModel;
import com.github.awvalenti.bauhinia.nitida.model.NitidaOutput;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.console.NitidaConsole;
import com.github.awvalenti.bauhinia.nitida.view.window.NitidaWindow;

public class NitidaMain {

	public static void main(String[] args) {
		ProjectProperties projectProperties = new ProjectProperties();

		NitidaOutput view;
		NitidaModel model;

		if (args.length > 0 && args[0].equals("--console")) {
			view = new NitidaConsole(projectProperties);
			model = new NitidaModel(Forficata.syncConnector(1), view);
		} else {
			view = new NitidaWindow(projectProperties);
			model = new NitidaModel(Forficata.asyncConnector(1), view);
		}

		view.run();
		model.run();
	}

}
