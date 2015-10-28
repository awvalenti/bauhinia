package com.github.awvalenti.bauhinia.forficata.observers;

import com.github.awvalenti.bauhinia.forficata.CoronataFailure;
import com.github.awvalenti.bauhinia.forficata.Phase;

public interface CoronataPhaseObserver {

	void starting();

	void running(Phase phase);

	void success(Phase phase);

	void failure(Phase phase, CoronataFailure failure);

}
