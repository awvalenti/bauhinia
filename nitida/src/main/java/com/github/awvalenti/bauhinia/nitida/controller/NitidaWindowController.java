package com.github.awvalenti.bauhinia.nitida.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.awvalenti.bauhinia.nitida.model.NitidaModel;
import com.github.awvalenti.bauhinia.nitida.model.Profile;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction.ProfileComboBox;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction.RetryButton;

public class NitidaWindowController {

	public NitidaWindowController(final NitidaModel model,
			RetryButton retryButton, final ProfileComboBox comboBox) {
		retryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.run();
			}
		});

	 comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.profileChanged((Profile) comboBox.getSelectedItem());
			}
		});
	}

}
