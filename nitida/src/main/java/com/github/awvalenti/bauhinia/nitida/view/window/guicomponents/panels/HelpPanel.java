package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.nitida.view.common.properties.ProjectProperties;
import com.github.awvalenti.bauhinia.nitida.view.window.BrowserLauncher;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.interaction.HyperlinkButton;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.InformationPane.HorizontalScrolling;

public class HelpPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final String ABOUT_PATH = "/nitida-license.txt";

	private static final String USAGE_OFFLINE_PATH =
			"/com/github/awvalenti/bauhinia/nitida/usage.md";

	private static final String USAGE_ONLINE_URL_PATTERN =
			"https://github.com/awvalenti/bauhinia/tree/" +
			"${version}" +
			"/nitida/src/main/resources/com/github/awvalenti/bauhinia/nitida/usage.md";

	private final ProjectProperties projectProperties;
	private final BrowserLauncher browserLauncher;

	public HelpPanel(ProjectProperties projectProperties, BrowserLauncher browserLauncher) {
		this.projectProperties = projectProperties;
		this.browserLauncher = browserLauncher;

		setBorder(BorderFactory.createTitledBorder("Help"));
		add(new HyperlinkButton("About", new AboutActionListener()));
		add(new HyperlinkButton("Usage<br>(offline)", new UsageOfflineActionListener()));
		add(new HyperlinkButton("Usage<br>(online)", new UsageOnlineActionListener()));
	}

	private class AboutActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			showDialog("About", 600, 550, ABOUT_PATH);
		}

	}

	private class UsageOfflineActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			showDialog("Usage", 850, 800, USAGE_OFFLINE_PATH);
		}
	}

	public class UsageOnlineActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String url = USAGE_ONLINE_URL_PATTERN.replace("${version}",
					projectProperties.getProjectVersion());

			browserLauncher.open(url);
		}
	}

	private void showDialog(String title, int width, int height, String pathToTextResource) {
		JDialog dialog = new JDialog();
		dialog.setModal(true);
		dialog.setTitle(title);
		dialog.setSize(width, height);
		dialog.add(new InformationPane(HorizontalScrolling.AS_NEEDED, new Font(Font.MONOSPACED,
				Font.BOLD, 14), getClass().getResource(pathToTextResource)));
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}

}
