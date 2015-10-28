package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.Phase.*;

import com.github.awvalenti.bauhinia.coronata.CoronataException;
import com.github.awvalenti.bauhinia.coronata.CoronataFailure;
import com.github.awvalenti.bauhinia.coronata.Phase;
import com.github.awvalenti.bauhinia.coronata.Wiimote;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;

class PhaseObserverAdapter implements CoronataObserver {

	private final CoronataPhaseObserver output;

	private Phase currentPhase;
	private boolean identified;

	public PhaseObserverAdapter(CoronataPhaseObserver output) {
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
	}

	@Override
	public void deviceRejectedIdentification(String address, String deviceClass) {
	}

	@Override
	public void deviceIdentifiedAsNotWiimote(String address, String deviceClass) {
	}

	@Override
	public void wiimoteIdentified() {
		identified = true;
		output.success(FIND_WIIMOTE);
		moveToPhase(CONNECT_TO_WIIMOTE);
	}

	@Override
	public void wiimoteConnected(Wiimote wiimote) {
		output.success(CONNECT_TO_WIIMOTE);
	}

	@Override
	public void searchFinished() {
		// TODO Provide failure information
		if (!identified) output.failure(FIND_WIIMOTE, new CoronataFailure(new Exception(), ""));
	}

	@Override
	public void errorOccurred(CoronataException e) {
		// TODO Provide failure information
		output.failure(currentPhase, new CoronataFailure(e, ""));
	}

}
