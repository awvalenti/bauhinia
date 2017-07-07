package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.nitida.view.window.BrowserLauncher;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.buttons.HyperlinkButton;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.InformationPane.HorizontalScrolling;

public class HelpPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String NITIDA_HOW_TO_USE_URL = "https://github.com/awvalenti/bauhinia/tree/master/nitida#how-to-use";
	private static final String NITIDA_LICENSE_PATH = "/nitida-license.txt";

	private final BrowserLauncher browserLauncher;

	public HelpPanel(BrowserLauncher browserLauncher) {
		this.browserLauncher = browserLauncher;
		setBorder(BorderFactory.createTitledBorder("Help"));
		add(new HyperlinkButton("How to use", new HowToUseActionListener()));
		add(new HyperlinkButton("About", new AboutActionListener()));
	}

	private class HowToUseActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			browserLauncher.open(NITIDA_HOW_TO_USE_URL);
		}
	}

	private class AboutActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JDialog dialog = new JDialog();
			dialog.setModal(true);
			dialog.setTitle("About");
			dialog.setSize(600, 500);
			dialog.add(new InformationPane(HorizontalScrolling.AS_NEEDED, new Font(Font.MONOSPACED,
					Font.PLAIN, 14), getClass().getResource(NITIDA_LICENSE_PATH)));
			dialog.setLocationRelativeTo(null);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		}
	}

}
