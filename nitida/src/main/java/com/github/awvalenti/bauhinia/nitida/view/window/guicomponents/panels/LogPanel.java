package com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemote;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;
import com.github.awvalenti.bauhinia.nitida.view.common.properties.Messages;
import com.github.awvalenti.bauhinia.nitida.view.window.guicomponents.panels.InformationPane.HorizontalScrolling;

public class LogPanel extends JPanel implements CoronataLifecycleEventsObserver {

	private static final long serialVersionUID = 1L;

	private final Messages messages;
	private final InformationPane informationPane = new InformationPane(HorizontalScrolling.NEVER);

	public LogPanel(Messages messages) {
		super(new BorderLayout());
		this.messages = messages;
		setBorder(BorderFactory.createTitledBorder("Log"));
		add(informationPane);
	}

	private synchronized void append(String content) {
		informationPane.append("\u2022 " + content + "\n");
	}

	@Override
	public void coronataStarted() {
	}

	@Override
	public void libraryLoadedSearchStarted() {
		append(messages.get("libraryLoadedSearchStarted"));
	}

	@Override
	public void bluetoothDeviceFound(String btAddress, String deviceClass) {
		append(messages.get("bluetoothDeviceFound", btAddress, deviceClass));
	}

	@Override
	public void identificationRejected(String btAddress) {
		append(messages.get("identificationRejected", btAddress));
	}

	@Override
	public void identifiedAsNonWiiRemote(String btAddress) {
		append(messages.get("identifiedAsNonWiiRemote", btAddress));
	}

	@Override
	public void identifiedAsWiiRemote(String btAddressOrNull) {
		append(btAddressOrNull == null ?
				messages.get("identifiedAsWiiRemote-addressNull") :
				messages.get("identifiedAsWiiRemote-addressNotNull",
						btAddressOrNull));
	}

	@Override
	public void connectionRejected(String btAddress) {
		append(messages.get("connectionRejected", btAddress));
	}

	@Override
	public void connected(CoronataWiiRemote wiiRemote) {
		append(messages.get("connected"));
	}

	@Override
	public void searchFinished() {
		append(messages.get("searchFinished"));
	}

	@Override
	public void disconnected() {
		append(messages.get("disconnected"));
	}

	@Override
	public void errorOccurred(CoronataException e) {
		append(e.getMessage());
	}

}
