package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.Desktop;
import java.awt.Desktop.Action;
import java.io.IOException;
import java.net.URI;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class BrowserLauncher {

	private final LaunchingStrategy strategy;

	public BrowserLauncher() {
		strategy = LaunchingStrategy.detectFromOs();
	}

	public void open(String url) {
		try {
			strategy.launch(url);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static enum LaunchingStrategy {

		DESKTOP_BROWSE {
			@Override
			public void launch(String url) throws IOException {
				Desktop.getDesktop().browse(URI.create(url));
			}
		},

		XDG_OPEN_COMMAND {
			@Override
			public void launch(String url) throws IOException {
				Runtime.getRuntime().exec("xdg-open " + url);
			}
		},

		OPEN_COMMAND {
			@Override
			public void launch(String url) throws IOException {
				Runtime.getRuntime().exec("open " + url);
			}
		},

		BROWSER_LAUNCHING_NOT_SUPPORTED {
			@Override
			public void launch(String url) throws IOException {
				JTextArea textArea = new JTextArea("Please visit: " + url);
				textArea.setEditable(false);
				textArea.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				JOptionPane.showMessageDialog(null, textArea, "Manual web browser launch",
						JOptionPane.INFORMATION_MESSAGE);
			}
		},
		;

		public static LaunchingStrategy detectFromOs() {
			if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Action.BROWSE)) {
				return DESKTOP_BROWSE;
			} else {
				String os = System.getProperty("os.name").toLowerCase();
				if (os.contains("nix") || os.contains("nux") || os.contains("aix")) return XDG_OPEN_COMMAND;
				else if (os.contains("mac")) return OPEN_COMMAND;
				else return BROWSER_LAUNCHING_NOT_SUPPORTED;
			}
		}

		public abstract void launch(String url) throws IOException;

	}

}
