package com.github.awvalenti.bauhinia.forficata.observers;

import com.github.awvalenti.bauhinia.forficata.ForficataFailure;
import com.github.awvalenti.bauhinia.forficata.Phase;

public interface ForficataPhaseObserver {

	void running(Phase phase);

	void success(Phase phase);

	void failure(Phase phase, ForficataFailure failure);

}
