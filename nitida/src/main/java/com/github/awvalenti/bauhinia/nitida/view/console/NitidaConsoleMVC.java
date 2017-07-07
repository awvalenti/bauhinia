package com.github.awvalenti.bauhinia.nitida.view.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.github.awvalenti.bauhinia.coronata.CoronataBuilder;
import com.github.awvalenti.bauhinia.coronata.CoronataBuilderStep3;
import com.github.awvalenti.bauhinia.nitida.NitidaMain;
import com.github.awvalenti.bauhinia.nitida.model.NitidaModel;
import com.github.awvalenti.bauhinia.nitida.other.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.Messages;

public class NitidaConsoleMVC {

	public NitidaConsoleMVC() {
		printCopyrightInfo();

		CoronataBuilderStep3 builder = CoronataBuilder.beginConfig()
				.synchronous()
				.oneWiiRemote()
				.onLifecycleEvents(new NitidaViewConsole(new ProjectProperties(), new Messages()));

		NitidaModel model = new NitidaModel(builder);
		model.setCoronata(builder.endConfig());
		model.run();
	}

	private void printCopyrightInfo() {
		String copyrightNoticePath =
				"/com/github/awvalenti/bauhinia/nitida/nitida-copyright-notice.txt";

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				NitidaMain.class.getResourceAsStream(copyrightNoticePath)));

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
