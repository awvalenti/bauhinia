package com.github.awvalenti.bauhinia.coronata;

import static com.github.awvalenti.bauhinia.coronata.State.RunPolicy.*;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;
import javax.microedition.io.Connector;

class OpenDataPipeState extends State {

	private final StateFactory states;

	private final String btAddress;
	private final L2CAPConnection controlPipe;

	OpenDataPipeState(StateFactory states, String btAddress,
			L2CAPConnection controlPipe) {
		super(STOP_ONLY_IF_REQUESTED);
		this.states = states;
		this.btAddress = btAddress;
		this.controlPipe = controlPipe;
	}

	@Override
	void cleanUpIfDidntRun() {
		closeControlPipe();
	}

	@Override
	State run() {
		final L2CAPConnection dataPipe;
		try {
			dataPipe = (L2CAPConnection) Connector.open(
					String.format("btl2cap://%s:13", btAddress),
					Connector.READ_WRITE, true);
		} catch (IOException e) {
			closeControlPipe();
			return states.connectionRejected(btAddress);
		}

		return states.connectionAccepted(
				new WiiRemoteConnection(controlPipe, dataPipe));
	}

	private void closeControlPipe() {
		try {
			controlPipe.close();
		} catch (IOException e2) {
			// Nothing can be done here
		}
	}

}
