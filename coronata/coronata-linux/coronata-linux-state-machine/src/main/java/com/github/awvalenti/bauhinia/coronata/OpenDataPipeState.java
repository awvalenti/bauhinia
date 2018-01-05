package com.github.awvalenti.bauhinia.coronata;

import java.io.IOException;

import javax.bluetooth.L2CAPConnection;
import javax.microedition.io.Connector;

class OpenDataPipeState extends State {

	private final StateFactory states;

	private final String btAddress;
	private final L2CAPConnection controlPipe;

	private L2CAPConnection dataPipe;

	OpenDataPipeState(StateFactory states, String btAddress,
			L2CAPConnection controlPipe) {
		this.states = states;
		this.btAddress = btAddress;
		this.controlPipe = controlPipe;
	}

	@Override
	boolean shouldStopNow(boolean stopRequested, TimeoutCoundown timeout) {
		return stopRequested;
	}

	@Override
	State run() {
		try {
			dataPipe = (L2CAPConnection) Connector.open(
					String.format("btl2cap://%s:13", btAddress),
					Connector.READ_WRITE, true);
		} catch (IOException e) {
			closePipes();
			return states.connectionRejected(btAddress);
		}

		return states.connectionAccepted(
				new WiiRemoteConnection(controlPipe, dataPipe));
	}

	@Override
	void cleanUpIfStoppedHere() {
		closePipes();
	}

	private void closePipes() {
		try {
			try {
				controlPipe.close();
			} finally {
				if (dataPipe != null) dataPipe.close();
			}
		} catch (IOException e) {
			// Nothing can be done here
		}
	}

}
