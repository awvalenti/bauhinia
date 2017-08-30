package com.github.awvalenti.bauhinia.coronata.observers;

import com.github.awvalenti.bauhinia.coronata.CoronataException;

public interface CoronataErrorObserver {

	void errorOccurred(CoronataException e);

}
