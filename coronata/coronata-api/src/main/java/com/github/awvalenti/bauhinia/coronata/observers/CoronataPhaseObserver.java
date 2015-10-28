package com.github.awvalenti.bauhinia.coronata.observers;

import com.github.awvalenti.bauhinia.coronata.CoronataFailure;
import com.github.awvalenti.bauhinia.coronata.Phase;

public interface CoronataPhaseObserver {

	void starting();

	void running(Phase phase);

	void success(Phase phase);

	void failure(Phase phase, CoronataFailure failure);

}
