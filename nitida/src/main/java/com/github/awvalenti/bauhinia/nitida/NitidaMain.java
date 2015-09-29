package com.github.awvalenti.bauhinia.nitida;

import com.github.awvalenti.bauhinia.forficata.Forficata;
import com.github.awvalenti.bauhinia.forficata.ForficataBuilderStep1;
import com.github.awvalenti.bauhinia.nitida.controller.NitidaController;
import com.github.awvalenti.bauhinia.nitida.model.NitidaModel;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.console.NitidaConsole;
import com.github.awvalenti.bauhinia.nitida.view.window.NitidaWindow;
import com.github.awvalenti.bauhinia.nitida.view.window.RetryButton;

public class NitidaMain {

	public static void main(String[] args) {
		ProjectProperties projectProperties = new ProjectProperties();

		NitidaModel model;
		ForficataBuilderStep1 builder = Forficata.builder();

		if (args.length > 0 && args[0].equals("--console")) {
			model = new NitidaModel(builder.synchronousConnector(), new NitidaConsole(
					projectProperties));
			model.connect();

		} else {
			RetryButton retryButton = new RetryButton();
			NitidaWindow nitidaWindow = new NitidaWindow(projectProperties);
			model = new NitidaModel(builder.asynchronousConnector());
			new NitidaController(model, retryButton);
			nitidaWindow.run();
			model.connect();
		}

	}

}
