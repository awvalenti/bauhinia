package com.github.awvalenti.bauhinia.nitida;

import com.github.awvalenti.bauhinia.nitida.model.NitidaModel;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.console.NitidaConsole;
import com.github.awvalenti.bauhinia.nitida.view.window.NitidaWindow;
import com.github.awvalenti.bauhinia.nitida.view.window.StatePanel;

public class NitidaMain {

	public static void main(String[] args) {
		if (args.length > 0 && args[0].equals("--console")) {
			new NitidaConsole(new ProjectProperties()).run();
		} else {
			NitidaWindow nitidaWindow = new NitidaWindow(new ProjectProperties(), new StatePanel());
			NitidaModel model = new NitidaModel(nitidaWindow);
			nitidaWindow.show();
			model.run();
		}
	}

}
