package com.github.awvalenti.bauhinia.coronata;

public interface Coronata extends Runnable {

	@Override
	void run();

	void requestStop();

}
