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
	public void libraryLoaded() {
		append(messages.get("libraryLoaded"));
	}

	@Override
	public void searchStarted() {
		append(messages.get("searchStarted"));
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		append(messages.get("bluetoothDeviceFound", address, deviceClass));
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
		append(messages.get("deviceRejectedIdentification", address));
	}

	@Override
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
		append(messages.get("deviceIdentifiedAsNotWiiRemote", address));
	}

	@Override
	public void wiiRemoteIdentified() {
		append(messages.get("wiiRemoteIdentified"));
	}

	@Override
	public void connected(CoronataWiiRemote wiiRemote) {
		append(messages.get("wiiRemoteConnected"));
	}

	@Override
	public void searchFinished() {
		append(messages.get("searchFinished"));
	}

	@Override
	public void disconnected() {
		append(messages.get("wiiRemoteDisconnected"));
	}

	@Override
	public void errorOccurred(CoronataException e) {
		append(e.getMessage());
	}

}
