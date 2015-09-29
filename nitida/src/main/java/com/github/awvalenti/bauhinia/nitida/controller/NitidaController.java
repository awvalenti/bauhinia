package com.github.awvalenti.bauhinia.nitida.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.awvalenti.bauhinia.nitida.model.NitidaControllable;
import com.github.awvalenti.bauhinia.nitida.view.window.RetryButton;

public class NitidaController {

	public NitidaController(final NitidaControllable model, RetryButton retryButton) {
		retryButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.connect();
			}
		});
	}

}
