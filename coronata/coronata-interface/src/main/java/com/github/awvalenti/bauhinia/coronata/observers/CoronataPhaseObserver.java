package com.github.awvalenti.bauhinia.coronata.observers;

import com.github.awvalenti.bauhinia.coronata.CoronataPhase;

public interface CoronataPhaseObserver {

	void reset();

	void running(CoronataPhase coronataPhase);

	void success(CoronataPhase coronataPhase);

	void failure(CoronataPhase coronataPhase);

}
