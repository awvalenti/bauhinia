package com.github.awvalenti.bauhinia.nitida;

import com.github.awvalenti.bauhinia.forficata.Forficata;
import com.github.awvalenti.bauhinia.forficata.ForficataBuilderStep1;
import com.github.awvalenti.bauhinia.nitida.controller.NitidaController;
import com.github.awvalenti.bauhinia.nitida.model.NitidaModel;
import com.github.awvalenti.bauhinia.nitida.model.NitidaOutputListener;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.console.NitidaConsole;
import com.github.awvalenti.bauhinia.nitida.view.window.NitidaWindow;
import com.github.awvalenti.bauhinia.nitida.view.window.RetryButton;

public class NitidaMain {

	public static void main(String[] args) {
		ProjectProperties projectProperties = new ProjectProperties();

		NitidaOutputListener view;
		NitidaModel model;
		ForficataBuilderStep1 builder = Forficata.builder();

		if (args.length > 0 && args[0].equals("--console")) {
			view = new NitidaConsole(projectProperties);
			model = new NitidaModel(builder.synchronousConnector(), view);

		} else {
			RetryButton retryButton = new RetryButton();
			view = new NitidaWindow(projectProperties, retryButton);
			model = new NitidaModel(builder.asynchronousConnector(), view);
			new NitidaController(model, retryButton);
		}

		view.run();
		model.startSearch();
	}

}
