package com.github.awvalenti.bauhinia.nitida.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.awvalenti.bauhinia.nitida.model.NitidaInputHandler;
import com.github.awvalenti.bauhinia.nitida.view.window.RetryButton;

public class NitidaController {

	public NitidaController(final NitidaInputHandler model, RetryButton retryButton) {
		retryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.startSearch();
			}
		});
	}

}
