package com.github.awvalenti.bauhinia.forficata;

import static com.github.awvalenti.bauhinia.forficata.Phase.*;

public class EventListenerToPhaseListenerAdapter implements ForficataEventListener {

	private final ForficataPhaseListener output;
	private Phase currentPhase;
	private boolean connected;

	public EventListenerToPhaseListenerAdapter(ForficataPhaseListener output) {
		this.output = output;
		setCurrentPhase(LOAD_LIBRARIES);
	}

	private void setCurrentPhase(Phase phase) {
		output.running(phase);
		currentPhase = phase;
	}

	@Override
	public void librariesLoaded() {
		output.success(LOAD_LIBRARIES);
		setCurrentPhase(FIND_WIIMOTE);
	}

	@Override
	public void searchStarted() {
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		setCurrentPhase(IDENTIFY_WIIMOTE);
	}

	@Override
	public void wiimoteIdentified() {
		output.success(FIND_WIIMOTE);
		output.success(IDENTIFY_WIIMOTE);
		setCurrentPhase(CONNECT_TO_WIIMOTE);
	}

	@Override
	public void wiimoteConnected(Wiimote wiimote) {
		connected = true;
		output.success(CONNECT_TO_WIIMOTE);
		output.wiimoteConnected(wiimote);
	}

	@Override
	public void searchFinished() {
		if (!connected) output.failure(FIND_WIIMOTE, new ForficataFailure(new Exception(), ""));
	}

	@Override
	public void errorOccurred(ForficataException e) {
		output.failure(currentPhase, new ForficataFailure(e, ""));
	}

}
