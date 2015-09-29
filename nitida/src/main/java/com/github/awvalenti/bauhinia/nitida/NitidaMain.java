package com.github.awvalenti.bauhinia.nitida;

import com.github.awvalenti.bauhinia.forficata.Forficata;
import com.github.awvalenti.bauhinia.forficata.ForficataBuilderStep3;
import com.github.awvalenti.bauhinia.forficata.Phase;
import com.github.awvalenti.bauhinia.nitida.controller.NitidaController;
import com.github.awvalenti.bauhinia.nitida.model.NitidaModel;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.console.NitidaConsole;
import com.github.awvalenti.bauhinia.nitida.view.window.ActionPanel;
import com.github.awvalenti.bauhinia.nitida.view.window.ApplicationStatePanel;
import com.github.awvalenti.bauhinia.nitida.view.window.LogPanel;
import com.github.awvalenti.bauhinia.nitida.view.window.MacroStatePanel;
import com.github.awvalenti.bauhinia.nitida.view.window.NitidaWindow;
import com.github.awvalenti.bauhinia.nitida.view.window.PhasePanel;
import com.github.awvalenti.bauhinia.nitida.view.window.RetryButton;

public class NitidaMain {

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("--console")) runNitidaConsole();
		else runNitidaWindow(new ProjectProperties());
	}

	private static void runNitidaWindow(ProjectProperties projectProperties) {
		NitidaModel model;
		ForficataBuilderStep3 builder = Forficata.guidedBuilder()
				.asynchronous()
				.oneWiimote();

		model = new NitidaModel(builder);

		RetryButton retryButton = new RetryButton();
		PhasePanel phasePanel = new PhasePanel(Phase.values());
		LogPanel logPanel = new LogPanel();
		MacroStatePanel macroStatePanel = new MacroStatePanel();

		builder
				.connectionStateObserver(retryButton)
				.connectionStateObserver(macroStatePanel)
				.phaseStateObserver(phasePanel)
				.fullObserver(logPanel);

		model.setConnector(builder.build());

		NitidaWindow nitidaWindow = new NitidaWindow(projectProperties,
				new ApplicationStatePanel(phasePanel, macroStatePanel), logPanel,
				new ActionPanel(retryButton));

		new NitidaController(model, retryButton);
		nitidaWindow.run();
		model.connect();
	}

	private static void runNitidaConsole() {
		NitidaModel model;
		ForficataBuilderStep3 builder = Forficata.guidedBuilder().synchronous().oneWiimote();
		model = new NitidaModel(builder);
		builder.fullObserver(new NitidaConsole(new ProjectProperties()));
		model.setConnector(builder.build());
		model.connect();
	}

}
