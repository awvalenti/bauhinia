package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

import com.github.awvalenti.bauhinia.forficata.ForficataException;
import com.github.awvalenti.bauhinia.forficata.Wiimote;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;

public class LogPanel extends JPanel implements ForficataObserver {

	private static final long serialVersionUID = 1L;

	private JEditorPane pane;

	public LogPanel() {
		super(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Log"));
		pane = new JEditorPane();
		pane.setEditable(false);
		add(new JScrollPane(pane, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
	}

	private void append(String content) {
		Document doc = pane.getDocument();
		try {
			doc.insertString(doc.getLength(), content, null);
			doc.insertString(doc.getLength(), "\n", null);
			pane.setCaretPosition(doc.getLength());
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void forficataStarted() {
		append("Forficata started");
	}

	@Override
	public void librariesLoaded() {
		append("Libraries loaded successfuly");
	}

	@Override
	public void searchStarted() {
		append("Searching for Wiimote...");
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		append(String.format("Found a Bluetooth device at %s: %s\n", address, deviceClass));
	}

	@Override
	public void wiimoteIdentified() {
		append("Found Wiimote. Connecting...");
	}

	@Override
	public void wiimoteConnected(Wiimote wiimote) {
		append("Connected. Remote control is active!");
	}

	@Override
	public void searchFinished() {
		append("Finished search");
	}

	@Override
	public void errorOccurred(ForficataException e) {
		append(e.getMessage());
	}

}
