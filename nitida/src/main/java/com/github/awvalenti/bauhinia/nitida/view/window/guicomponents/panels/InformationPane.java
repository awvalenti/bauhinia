package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels;

import java.awt.Font;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class InformationPane extends JScrollPane {

	private static final long serialVersionUID = 1L;

	private final JEditorPane editorPane;

	public InformationPane(HorizontalScrolling horizontalScrolling) {
		editorPane = new JEditorPane();
		editorPane.setEditable(false);

		setViewportView(editorPane);
		setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		setHorizontalScrollBarPolicy(horizontalScrolling.policy);
	}

	public InformationPane(HorizontalScrolling horizontalScrolling, Font font, URL contentUrl) {
		this(horizontalScrolling);
		editorPane.setFont(font);
		try {
			editorPane.setPage(contentUrl);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void append(String content) {
		Document doc = editorPane.getDocument();
		try {
			doc.insertString(doc.getLength(), content, null);
			doc.insertString(doc.getLength(), "\n", null);
			editorPane.setCaretPosition(doc.getLength());
		} catch (BadLocationException e) {
			throw new RuntimeException(e);
		}
	}

	public static enum HorizontalScrolling {
		AS_NEEDED(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED),
		NEVER(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER),
		;

		private final int policy;

		private HorizontalScrolling(int policy) {
			this.policy = policy;
		}
	}

}
