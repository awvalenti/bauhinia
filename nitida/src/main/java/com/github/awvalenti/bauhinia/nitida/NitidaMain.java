package com.github.awvalenti.bauhinia.nitida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.github.awvalenti.bauhinia.coronata.Coronata;
import com.github.awvalenti.bauhinia.coronata.CoronataBuilderStep3;
import com.github.awvalenti.bauhinia.nitida.controller.NitidaController;
import com.github.awvalenti.bauhinia.nitida.model.NitidaModel;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.console.NitidaConsole;
import com.github.awvalenti.bauhinia.nitida.view.window.ActionPanel;
import com.github.awvalenti.bauhinia.nitida.view.window.ApplicationStatePanel;
import com.github.awvalenti.bauhinia.nitida.view.window.ConnectionStatePanel;
import com.github.awvalenti.bauhinia.nitida.view.window.HelpPanel;
import com.github.awvalenti.bauhinia.nitida.view.window.LogPanel;
import com.github.awvalenti.bauhinia.nitida.view.window.NitidaWindow;
import com.github.awvalenti.bauhinia.nitida.view.window.PhasePanel;
import com.github.awvalenti.bauhinia.nitida.view.window.RetryButton;
import com.github.awvalenti.bauhinia.nitida.view.window.UserInputPanel;

public class NitidaMain {

	private static final String COPYRIGHT_NOTICE_PATH = "/com/github/awvalenti/bauhinia/nitida/nitida-copyright-notice.txt";

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("--console")) runNitidaConsole();
		else runNitidaWindow();
	}

	private static void runNitidaWindow() {
		CoronataBuilderStep3 builder = Coronata.guidedBuilder()
				.asynchronous()
				.oneWiiRemote();

		NitidaModel model = new NitidaModel(builder);

		RetryButton retryButton = new RetryButton();
		PhasePanel phasePanel = new PhasePanel();
		LogPanel logPanel = new LogPanel();
		ConnectionStatePanel connectionStatePanel = new ConnectionStatePanel();

		builder
				.phaseStateObserver(phasePanel)
				.fullObserver(logPanel)
				.connectionStateObserver(connectionStatePanel)
				.connectionStateObserver(retryButton);

		NitidaWindow nitidaWindow = new NitidaWindow(new ProjectProperties(),
				new ApplicationStatePanel(phasePanel, connectionStatePanel), logPanel,
				new UserInputPanel(new ActionPanel(retryButton), new HelpPanel()));

		new NitidaController(model, retryButton);
		nitidaWindow.run();

		model.setConnector(builder.build());
		model.connect();
	}

	private static void runNitidaConsole() {
		printCopyrightInfo();

		CoronataBuilderStep3 builder = Coronata.guidedBuilder()
				.synchronous()
				.oneWiiRemote();

		NitidaModel model = new NitidaModel(builder);

		builder.fullObserver(new NitidaConsole(new ProjectProperties()));

		model.setConnector(builder.build());
		model.connect();
	}

	private static void printCopyrightInfo() {
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				NitidaMain.class.getResourceAsStream(COPYRIGHT_NOTICE_PATH)));

		try {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			System.out.println();

		} catch (IOException e) {
			throw new RuntimeException(e);

		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
