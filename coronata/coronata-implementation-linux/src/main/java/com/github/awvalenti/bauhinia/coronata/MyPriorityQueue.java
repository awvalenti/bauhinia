package com.github.awvalenti.bauhinia.coronata;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class MyPriorityQueue<E extends Comparable<E>> {

	private final ArrayList<E> sortedElements = new ArrayList<E>();

	public void push(E t) {
		sortedElements.add(indexFor(t), t);
	}

	public E pop() {
		if (isEmpty()) throw new NoSuchElementException("Empty list");
		return sortedElements.remove(sortedElements.size() - 1);
	}

	public final boolean isEmpty() {
		return sortedElements.isEmpty();
	}

	private int indexFor(E e) {
		int i;
		for (i = 0; i < sortedElements.size(); ++i) {
			if (e.compareTo(sortedElements.get(i)) <= 0) break;
		}
		return i;
	}

}
