package com.github.awvalenti.bauhinia.coronata.observers;

import com.github.awvalenti.bauhinia.coronata.WiiRemote;

public interface CoronataWiiRemoteConnectionObserver {

	void wiiRemoteConnected(WiiRemote wiiRemote);

	void wiiRemoteDisconnected(WiiRemote wiiRemote);

}
