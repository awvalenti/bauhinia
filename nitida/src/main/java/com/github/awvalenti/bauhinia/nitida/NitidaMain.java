package com.github.awvalenti.bauhinia.nitida;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.github.awvalenti.bauhinia.coronata.Coronata;
import com.github.awvalenti.bauhinia.coronata.CoronataBuilder;
import com.github.awvalenti.bauhinia.coronata.CoronataBuilderStep3;
import com.github.awvalenti.bauhinia.nitida.controller.NitidaController;
import com.github.awvalenti.bauhinia.nitida.model.NitidaModel;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.Messages;
import com.github.awvalenti.bauhinia.nitida.view.console.NitidaConsole;
import com.github.awvalenti.bauhinia.nitida.view.window.ActionPanel;
import com.github.awvalenti.bauhinia.nitida.view.window.ApplicationStatePanel;
import com.github.awvalenti.bauhinia.nitida.view.window.BrowserLauncher;
import com.github.awvalenti.bauhinia.nitida.view.window.LifecycleStatePanel;
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
		PhasePanel phasePanel = new PhasePanel();
		LifecycleStatePanel lifecycleStatePanel = new LifecycleStatePanel();
		LogPanel logPanel = new LogPanel(new Messages());
		RetryButton retryButton = new RetryButton();

		CoronataBuilderStep3 builder = CoronataBuilder.beginConfig()
				.asynchronous()
				.oneWiiRemote()
				.onPhase(phasePanel)
				.onLifecycleState(lifecycleStatePanel)
				.onLifecycleEvents(logPanel)
				.onLifecycleState(retryButton);

		NitidaModel model = new NitidaModel(builder);

		Coronata coronata = builder.endConfig();

		new NitidaController(model, retryButton);

		NitidaWindow view = new NitidaWindow(
				new ProjectProperties(),
				new ApplicationStatePanel(phasePanel, lifecycleStatePanel),
				logPanel,
				new UserInputPanel(
						new ActionPanel(retryButton),
						new HelpPanel(new BrowserLauncher())));

		view.run();

		model.setCoronata(coronata);
		model.run();
	}

	private static void runNitidaConsole() {
		printCopyrightInfo();

		CoronataBuilderStep3 builder = CoronataBuilder.beginConfig()
				.synchronous()
				.oneWiiRemote();

		NitidaModel model = new NitidaModel(builder);

		builder.onLifecycleEvents(new NitidaConsole(new ProjectProperties(), new Messages()));

		model.setCoronata(builder.endConfig());
		model.run();
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
