package com.github.awvalenti.bauhinia.forficata.api;

import java.io.IOException;

public interface Wiimote {

	void setListener(WiimoteListener listener);

	void turnLedOn(int ledIndex) throws IOException;

	void startVibration() throws IOException;

	void stopVibration() throws IOException;

}
