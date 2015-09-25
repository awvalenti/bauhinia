package com.github.awvalenti.bauhinia.nitida.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.github.awvalenti.bauhinia.nitida.model.NitidaInputHandler;
import com.github.awvalenti.bauhinia.nitida.view.window.ConnectButton;

public class NitidaController {

	public NitidaController(final NitidaInputHandler model, ConnectButton connectButton) {
		connectButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.startSearch();
			}
		});
	}

}
