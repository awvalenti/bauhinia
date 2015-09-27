package com.github.awvalenti.bauhinia.nitida.model;

import com.github.awvalenti.bauhinia.forficata.ForficataFailure;
import com.github.awvalenti.bauhinia.forficata.Phase;

public interface NitidaOutputListener {

	void run();

	void running(Phase phase);

	void success(Phase phase);

	void failure(Phase phase, ForficataFailure failure);

	void wiimoteDisconnected();

}
