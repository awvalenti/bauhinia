package com.github.awvalenti.bauhinia.nitida.view.console;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.CoronataWiiRemote;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;
import com.github.awvalenti.bauhinia.nitida.view.common.properties.Messages;
import com.github.awvalenti.bauhinia.nitida.view.common.properties.ProjectProperties;

public class NitidaConsoleView implements CoronataLifecycleEventsObserver {

	private final ProjectProperties projectProperties;
	private final Messages messages;

	public NitidaConsoleView(ProjectProperties projectProperties,
			Messages messages) {
		this.projectProperties = projectProperties;
		this.messages = messages;
	}

	@Override
	public void coronataStarted() {
		System.out.println(messages.get("appTitle",
				projectProperties.getProjectVersion()));
	}

	@Override
	public void searchStarted(boolean isWindows) {
		System.out.println(messages.get(
				isWindows ? "searchStarted-windows" : "searchStarted-linux"));
	}

	@Override
	public void bluetoothDeviceFound(String btAddress, String deviceClass) {
		System.out.println(
				messages.get("bluetoothDeviceFound", btAddress, deviceClass));
	}

	@Override
	public void identifiedAsWiiRemote(String btAddressOrNull) {
		System.out.println(btAddressOrNull == null ?
				messages.get("identifiedAsWiiRemote-addressNull") :
				messages.get("identifiedAsWiiRemote-addressNotNull",
						btAddressOrNull));
	}

	@Override
	public void identificationRejected(String btAddress) {
		System.out.println(messages.get("identificationRejected", btAddress));
	}

	@Override
	public void identifiedAsNonWiiRemote(String btAddress) {
		System.out.println(messages.get("identifiedAsNonWiiRemote", btAddress));
	}

	@Override
	public void connectionRejected(String btAddress) {
		System.out.println(messages.get("connectionRejected", btAddress));
	}

	@Override
	public void connected(CoronataWiiRemote wiiRemote) {
		System.out.println(messages.get("connected"));
	}

	@Override
	public void searchFinished() {
		System.out.println(messages.get("searchFinished"));
	}

	@Override
	public void disconnected() {
		System.out.println(messages.get("disconnected"));
	}

	@Override
	public void errorOccurred(CoronataException e) {
		e.printStackTrace();
		System.err.println("\n" + e.getMessage());
	}

}
