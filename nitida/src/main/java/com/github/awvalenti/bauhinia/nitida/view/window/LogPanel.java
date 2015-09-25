package com.github.awvalenti.bauhinia.nitida.view.window;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class LogPanel extends JPanel {

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

	public void append(String content) {
		Document doc = pane.getDocument();
		try {
			doc.insertString(doc.getLength(), content, null);
			doc.insertString(doc.getLength(), "\n", null);
			pane.setCaretPosition(doc.getLength());
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
	}

}
