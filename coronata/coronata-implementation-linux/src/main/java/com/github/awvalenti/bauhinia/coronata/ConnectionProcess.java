package com.github.awvalenti.bauhinia.coronata;

import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DeviceClass;
import javax.bluetooth.RemoteDevice;

import com.github.awvalenti.bauhinia.coronata.WiiRemoteFactory.ConnectionRejected;
import com.github.awvalenti.bauhinia.coronata.WiiRemoteFactory.IdentificationRejected;
import com.github.awvalenti.bauhinia.coronata.WiiRemoteFactory.IdentifiedAsNonWiiRemote;
import com.github.awvalenti.bauhinia.coronata.observers.CoronataLifecycleEventsObserver;

// TODO Refactor
class ConnectionProcess implements Runnable {

	private final Data data;

	ConnectionProcess(int minTimeoutInSeconds, int expectedWiiRemotesCount,
			CoronataLifecycleEventsObserver leObserver,
			WiiRemoteFactory wiiRemoteFactory) {

		data = new Data(minTimeoutInSeconds, expectedWiiRemotesCount,
				leObserver, wiiRemoteFactory);
	}

	@Override
	public void run() {
		data.leObserver.coronataStarted();

		State state = State.LOAD_LIBRARY;
		while (state.shouldRun(data)) {
			state = state.run(data);
		}

		data.leObserver.searchFinished();
	}

	void requestStop() {
		data.stopRequested = true;
		if (data.blueCoveLib != null) data.blueCoveLib.stopSearch();
	}

	static class Data {

		final long startTime = System.nanoTime();

		final int minTimeoutInSeconds;
		final int expectedWiiRemotesCount;
		final CoronataLifecycleEventsObserver leObserver;
		final WiiRemoteFactory wiiRemoteFactory;

		final BlueCoveExceptionFactory exceptionFactory =
				new BlueCoveExceptionFactory();

		public Data(int minTimeoutInSeconds, int expectedWiiRemotesCount,
				CoronataLifecycleEventsObserver leObserver,
				WiiRemoteFactory wiiRemoteFactory) {
			this.minTimeoutInSeconds = minTimeoutInSeconds;
			this.expectedWiiRemotesCount = expectedWiiRemotesCount;
			this.leObserver = leObserver;
			this.wiiRemoteFactory = wiiRemoteFactory;
		}

		volatile boolean stopRequested;

		int connectedWiiRemotesCount;
		BlueCoveLibraryFacade blueCoveLib;
		BluetoothStateException exception;
		CandidatesQueue candidates;
		Candidate candidate;
		BlueCoveWiiRemote wiiRemote;

		boolean timeoutReached() {
			return (System.nanoTime() - startTime) / 1e9 >= minTimeoutInSeconds;
		}

	}

	enum State {

		LOAD_LIBRARY {
			@Override
			State run(Data data) {
				try {
					data.blueCoveLib = new BlueCoveLibraryFacade();
					data.leObserver.libraryLoaded();
					return INQUIRY;

				} catch (BluetoothStateException e) {
					data.exception = e;
					return BLUETOOTH_STATE_EXCEPTION;
				}
			}
		},

		INQUIRY {
			@Override
			State run(final Data data) {
				data.leObserver.searchStarted();

				data.candidates = new CandidatesQueue();

				CandidatesGatherer gatherer = new CandidatesGatherer();

				try {
					data.blueCoveLib.startSynchronousSearch(gatherer);

					data.candidates = gatherer.getCandidates();

					return INFORM_CANDIDATES;

				} catch (BluetoothStateException e) {
					data.exception = e;
					return BLUETOOTH_STATE_EXCEPTION;
				}

			}

			class CandidatesGatherer implements SimpleDiscoveryListener {
				private final CandidatesQueue candidates =
						new CandidatesQueue();

				@Override
				public void deviceDiscovered(RemoteDevice btDevice,
						DeviceClass deviceClass) {
					candidates.push(new Candidate(btDevice, deviceClass));
				}

				CandidatesQueue getCandidates() {
					return candidates;
				}

			}

		},

		BLUETOOTH_STATE_EXCEPTION {
			@Override
			State run(Data data) {
				data.leObserver.errorOccurred(
						data.exceptionFactory.correspondingTo(data.exception));
				return FINISHED;
			}

		},

		INFORM_CANDIDATES {
			@Override
			State run(Data data) {
				for (Candidate c : data.candidates) {
					data.leObserver.bluetoothDeviceFound(
							c.btDevice.getBluetoothAddress(),
							((Object) c.clazz).toString());
				}
				return IDENTIFY_NEXT_DEVICE;
			}
		},

		IDENTIFY_NEXT_DEVICE {
			@Override
			State run(Data data) {
				if (data.candidates.isEmpty()) return INQUIRY;

				data.candidate = data.candidates.pop();

				try {
					data.wiiRemoteFactory
							.assertDeviceIsWiiRemote(data.candidate.btDevice);
					return CONNECT;

				} catch (IdentificationRejected e) {
					return IDENTIFICATION_REJECTED;

				} catch (IdentifiedAsNonWiiRemote e) {
					return IDENTIFIED_AS_NON_WII_REMOTE;
				}
			}
		},

		IDENTIFICATION_REJECTED {
			@Override
			State run(Data data) {
				data.leObserver.identificationRejected(
						data.candidate.btDevice.getBluetoothAddress());
				return IDENTIFY_NEXT_DEVICE;
			}

		},

		IDENTIFIED_AS_NON_WII_REMOTE {
			@Override
			State run(Data data) {
				data.leObserver.identifiedAsNonWiiRemote(
						data.candidate.btDevice.getBluetoothAddress());
				return IDENTIFY_NEXT_DEVICE;
			}

		},

		CONNECT(RunPolicy.EVEN_IF_TIMEOUT_REACHED) {
			@Override
			State run(Data data) {
				data.leObserver.identifiedAsWiiRemote(
						data.candidate.btDevice.getBluetoothAddress());
				try {
					data.wiiRemote = data.wiiRemoteFactory.create(
							data.candidate.btDevice.getBluetoothAddress());
					data.leObserver.connected(data.wiiRemote);
					return CONNECTION_ACCEPTED;

				} catch (ConnectionRejected e) {
					return CONNECTION_REJECTED;
				}
			}
		},

		CONNECTION_REJECTED(RunPolicy.ALWAYS) {
			@Override
			State run(Data data) {
				data.leObserver.connectionRejected(
						data.candidate.btDevice.getBluetoothAddress());
				return IDENTIFY_NEXT_DEVICE;
			}

		},

		CONNECTION_ACCEPTED(RunPolicy.ALWAYS) {
			@Override
			State run(Data data) {
				if (data.stopRequested) {
					data.wiiRemote.disconnect();
					return FINISHED;

				} else if (++data.connectedWiiRemotesCount == data.expectedWiiRemotesCount) {
					return FINISHED;

				} else {
					return IDENTIFY_NEXT_DEVICE;
				}
			}

		},

		FINISHED(RunPolicy.NEVER) {
			@Override
			State run(Data data) {
				throw new UnsupportedOperationException("Shouldn't be called");
			}

		};

		private final RunPolicy runPolicy;

		State() {
			this(RunPolicy.DEFAULT);
		}

		State(RunPolicy runPolicy) {
			this.runPolicy = runPolicy;
		}

		abstract State run(Data data);

		final boolean shouldRun(Data data) {
			return runPolicy.shouldRun(data);
		}

		enum RunPolicy {
			ALWAYS {
				@Override
				boolean shouldRun(Data data) {
					return true;
				}
			},

			EVEN_IF_TIMEOUT_REACHED {
				@Override
				boolean shouldRun(Data data) {
					return !data.stopRequested;
				}
			},

			DEFAULT {
				@Override
				boolean shouldRun(Data data) {
					return !data.stopRequested && !data.timeoutReached();
				}
			},

			NEVER {
				@Override
				boolean shouldRun(Data data) {
					return false;
				}
			},

			;

			abstract boolean shouldRun(Data data);
		}

	}

}
