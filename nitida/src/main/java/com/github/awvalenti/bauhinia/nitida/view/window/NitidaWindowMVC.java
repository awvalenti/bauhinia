package com.github.awvalenti.bauhinia.nitida.view.window;

import com.github.awvalenti.bauhinia.coronata.CoronataBuilder;
import com.github.awvalenti.bauhinia.coronata.buildersteps.CoronataBuilderStep3;
import com.github.awvalenti.bauhinia.nitida.controller.NitidaWindowController;
import com.github.awvalenti.bauhinia.nitida.model.NitidaModel;
import com.github.awvalenti.bauhinia.nitida.view.common.properties.Messages;
import com.github.awvalenti.bauhinia.nitida.view.common.properties.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction.ProfileComboBox;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction.RetryButton;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.ActionPanel;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.ApplicationStatePanel;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.HelpPanel;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.LifecycleStatePanel;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.LogPanel;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.PhasesPanel;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.ProfilePanel;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.UserInputPanel;

public class NitidaWindowMVC {

	public NitidaWindowMVC() {
		PhasesPanel phasePanel = new PhasesPanel();
		LifecycleStatePanel lifecycleStatePanel = new LifecycleStatePanel();
		LogPanel logPanel = new LogPanel(new Messages());
		RetryButton retryButton = new RetryButton();

		CoronataBuilderStep3 builder = CoronataBuilder.beginConfig()
				.asynchronous()
				.oneWiiRemote()
				.onPhase(phasePanel)
				.onLifecycleEvents(logPanel)
				.onLifecycleState(lifecycleStatePanel)
				.onLifecycleState(retryButton);

		NitidaModel model = new NitidaModel(builder);

		model.setCoronata(builder.endConfig());

		ProfileComboBox comboBox = new ProfileComboBox();
		ProjectProperties projectProperties = new ProjectProperties();

		new NitidaWindowController(model, retryButton, comboBox);

		new NitidaWindowView(
				projectProperties,
				new ApplicationStatePanel(phasePanel, lifecycleStatePanel),
				logPanel,
				new UserInputPanel(
						new ProfilePanel(comboBox),
						new ActionPanel(retryButton),
						new HelpPanel(projectProperties, new BrowserLauncher())));

		model.run();
	}

}
