package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.CoronataPhase.*;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataFullObserver;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataPhaseObserver;

class PhaseObserverAdapter implements CoronataFullObserver {

	private final CoronataPhaseObserver output;

	private CoronataPhase currentPhase;
	private boolean identified;

	public PhaseObserverAdapter(CoronataPhaseObserver output) {
		this.output = output;
	}

	private void moveToPhase(CoronataPhase coronataPhase) {
		output.running(coronataPhase);
		currentPhase = coronataPhase;
	}

	@Override
	public void coronataStarted() {
		output.starting();
		moveToPhase(LOAD_LIBRARIES);
	}

	@Override
	public void librariesLoaded() {
		output.success(LOAD_LIBRARIES);
		moveToPhase(FIND_WII_REMOTE);
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
	public void deviceIdentifiedAsNotWiiRemote(String address, String deviceClass) {
	}

	@Override
	public void wiiRemoteIdentified() {
		identified = true;
		output.success(FIND_WII_REMOTE);
		moveToPhase(CONNECT_TO_WII_REMOTE);
	}

	@Override
	public void wiiRemoteConnected(WiiRemote wiiRemote) {
		output.success(CONNECT_TO_WII_REMOTE);
	}

	@Override
	public void searchFinished() {
		// TODO Provide failure information
		if (!identified) output.failure(FIND_WII_REMOTE);
	}

	@Override
	public void errorOccurred(CoronataException e) {
		// TODO Provide failure information
		output.failure(currentPhase);
	}

}
