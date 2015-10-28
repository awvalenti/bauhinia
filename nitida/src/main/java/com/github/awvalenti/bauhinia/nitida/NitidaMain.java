package com.github.awvalenti.bauhinia.nitida;

import com.github.awvalenti.bauhinia.forficata.Coronata;
import com.github.awvalenti.bauhinia.forficata.CoronataBuilderStep3;
import com.github.awvalenti.bauhinia.forficata.Phase;
import com.github.awvalenti.bauhinia.nitida.controller.NitidaController;
import com.github.awvalenti.bauhinia.nitida.model.NitidaModel;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.console.NitidaConsole;
import com.github.awvalenti.bauhinia.nitida.view.window.ActionPanel;
import com.github.awvalenti.bauhinia.nitida.view.window.ApplicationStatePanel;
import com.github.awvalenti.bauhinia.nitida.view.window.LogPanel;
import com.github.awvalenti.bauhinia.nitida.view.window.ConnectionStatePanel;
import com.github.awvalenti.bauhinia.nitida.view.window.NitidaWindow;
import com.github.awvalenti.bauhinia.nitida.view.window.PhasePanel;
import com.github.awvalenti.bauhinia.nitida.view.window.RetryButton;

public class NitidaMain {

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("--console")) runNitidaConsole();
		else runNitidaWindow();
	}

	private static void runNitidaWindow() {
		CoronataBuilderStep3 builder = Coronata.guidedBuilder()
				.asynchronous()
				.oneWiimote();

		NitidaModel model = new NitidaModel(builder);

		RetryButton retryButton = new RetryButton();
		PhasePanel phasePanel = new PhasePanel(Phase.values());
		LogPanel logPanel = new LogPanel();
		ConnectionStatePanel connectionStatePanel = new ConnectionStatePanel();

		builder
				.phaseStateObserver(phasePanel)
				.fullObserver(logPanel)
				.connectionStateObserver(connectionStatePanel)
				.connectionStateObserver(retryButton);

		NitidaWindow nitidaWindow = new NitidaWindow(new ProjectProperties(),
				new ApplicationStatePanel(phasePanel, connectionStatePanel), logPanel,
				new ActionPanel(retryButton));

		new NitidaController(model, retryButton);
		nitidaWindow.run();

		model.setConnector(builder.build());
		model.connect();
	}

	private static void runNitidaConsole() {
		CoronataBuilderStep3 builder = Coronata.guidedBuilder()
				.synchronous()
				.oneWiimote();

		NitidaModel model = new NitidaModel(builder);

		builder.fullObserver(new NitidaConsole(new ProjectProperties()));

		model.setConnector(builder.build());
		model.connect();
	}

}
