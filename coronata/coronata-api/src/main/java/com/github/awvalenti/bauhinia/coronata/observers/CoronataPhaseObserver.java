package com.github.awvalenti.bauhinia.coronata.observers;

import com.github.awvalenti.bauhinia.coronata.CoronataFailure;
import com.github.awvalenti.bauhinia.coronata.CoronataPhase;

public interface CoronataPhaseObserver {

	void starting();

	void running(CoronataPhase coronataPhase);

	void success(CoronataPhase coronataPhase);

	void failure(CoronataPhase coronataPhase, CoronataFailure failure);

}
