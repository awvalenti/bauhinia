package com.github.awvalenti.bauhinia.coronata;

class Counter {

	private final int goal;

	private int current = 0;

	Counter(int goal) {
		this.goal = goal;
	}

	void increment() {
		++current;
		if (current > goal) {
			throw new IllegalStateException(String.format(
					"Overflow: goal = %d, current = %d", goal, current));
		}
	}

	boolean reachedGoal() {
		return current == goal;
	}

}
