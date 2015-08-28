package com.github.awvalenti.bauhinia.test;

import java.io.IOException;

public class WiimoteDataTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		BlueCoveLibrary blueCoveLibrary = new BlueCoveLibrary();

		Object lock = new Object();
		blueCoveLibrary.startInquiry(new DeviceFoundListener(), lock);
		synchronized (lock) {
			lock.wait();
		}
	}

}
