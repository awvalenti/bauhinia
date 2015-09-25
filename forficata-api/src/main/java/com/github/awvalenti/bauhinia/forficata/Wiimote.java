package com.github.awvalenti.bauhinia.forficata;

import java.io.IOException;

public interface Wiimote {

	void setButtonListener(WiimoteButtonListener buttonListener);

	void setDisconnectionListener(WiimoteDisconnectionListener disconnectionListener);

	void turnLedOn(int ledIndex) throws IOException;

	void startVibration() throws IOException;

	void stopVibration() throws IOException;

}
