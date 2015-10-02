package com.github.awvalenti.bauhinia.forficata;

import static com.github.awvalenti.bauhinia.forficata.Phase.*;

import com.github.awvalenti.bauhinia.forficata.observers.ForficataObserver;
import com.github.awvalenti.bauhinia.forficata.observers.ForficataPhaseObserver;

class PhaseObserverAdapter implements ForficataObserver {

	private final ForficataPhaseObserver output;
	private Phase currentPhase;
	private boolean identified;

	public PhaseObserverAdapter(ForficataPhaseObserver output) {
		this.output = output;
	}

	private void moveToPhase(Phase phase) {
		output.running(phase);
		currentPhase = phase;
	}

	@Override
	public void forficataStarted() {
		output.starting();
		moveToPhase(LOAD_LIBRARIES);
	}

	@Override
	public void librariesLoaded() {
		output.success(LOAD_LIBRARIES);
		moveToPhase(FIND_WIIMOTE);
	}

	@Override
	public void searchStarted() {
	}

	@Override
	public void bluetoothDeviceFound(String address, String deviceClass) {
		moveToPhase(IDENTIFY_WIIMOTE);
	}

	@Override
	public void wiimoteIdentified() {
		identified = true;
		output.success(FIND_WIIMOTE);
		output.success(IDENTIFY_WIIMOTE);
		moveToPhase(CONNECT_TO_WIIMOTE);
	}

	@Override
	public void wiimoteConnected(Wiimote wiimote) {
		output.success(CONNECT_TO_WIIMOTE);
	}

	@Override
	public void searchFinished() {
		// TODO Provide failure information
		if (!identified) output.failure(FIND_WIIMOTE, new ForficataFailure(new Exception(), ""));
	}

	@Override
	public void errorOccurred(ForficataException e) {
		// TODO Provide failure information
		output.failure(currentPhase, new ForficataFailure(e, ""));
	}

}
