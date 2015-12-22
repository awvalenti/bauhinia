package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.nitida.view.window.InformationPane.HorizontalScrolling;

public class HelpPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String NITIDA_HOW_TO_USE_URL = "https://github.com/awvalenti/bauhinia/tree/master/nitida#how-to-use-it";
	private static final String NITIDA_LICENSE_PATH = "/nitida-license.txt";

	public HelpPanel() {
		setBorder(BorderFactory.createTitledBorder("Help"));
		add(new HyperlinkComponent("How to use", new HowToUseActionListener()));
		add(new HyperlinkComponent("About", new AboutActionListener()));
	}

	private final class HowToUseActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			try {
				Desktop.getDesktop().browse(URI.create(NITIDA_HOW_TO_USE_URL));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private final class AboutActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JDialog dialog = new JDialog();
			dialog.setTitle("About");
			dialog.setSize(600, 480);
			dialog.add(new InformationPane(HorizontalScrolling.AS_NEEDED, new Font(Font.MONOSPACED,
					Font.PLAIN, 14), getClass().getResource(NITIDA_LICENSE_PATH)));
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}

}
