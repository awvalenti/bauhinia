package com.github.awvalenti.forficata.api;

import java.io.IOException;

public interface Wiimote {

	void addListener(WiimoteListener listener);

	void turnLedOn(int ledIndex) throws IOException;

	void startVibration() throws IOException;

	void stopVibration() throws IOException;

}
