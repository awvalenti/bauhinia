package com.github.awvalenti.bauhinia.nitida.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.awvalenti.bauhinia.nitida.model.NitidaModel;
import com.github.awvalenti.bauhinia.nitida.model.Profile;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction.ProfileComboBox;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction.StartButton;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction.StopButton;

public class NitidaWindowController {

	public NitidaWindowController(final NitidaModel model,
			StartButton startButton, StopButton stopButton,
			final ProfileComboBox comboBox) {

		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.start();
			}
		});

		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.stop();
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
