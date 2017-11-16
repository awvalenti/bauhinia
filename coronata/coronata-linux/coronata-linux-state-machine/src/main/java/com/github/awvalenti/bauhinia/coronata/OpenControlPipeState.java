package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.State.RunPolicy.*;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;
import javax.microedition.io.Connector;

import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

class OpenControlPipeState extends State {

	private final StateFactory states;

	private final CoronataLifecycleEventsObserver leObserver;
	private final String btAddress;

	OpenControlPipeState(StateFactory states,
			CoronataLifecycleEventsObserver leObserver, String btAddress) {
		super(STOP_ONLY_IF_REQUESTED);
		this.states = states;
		this.leObserver = leObserver;
		this.btAddress = btAddress;
	}

	@Override
	State run() {
		leObserver.identifiedAsWiiRemote(btAddress);

		final L2CAPConnection controlPipe;
		try {
			controlPipe = (L2CAPConnection) Connector.open(
					String.format("btl2cap://%s:11", btAddress),
					Connector.WRITE, true);
		} catch (IOException e) {
			return states.connectionRejected(btAddress);
		}

		return states.openDataPipe(btAddress, controlPipe);
	}

}
