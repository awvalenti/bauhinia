package com.github.awvalenti.bauhinia.coronata;

import java.util.concurrent.atomic.AtomicInteger;

class JobSynchronizer {

	private static final AtomicInteger threadId = new AtomicInteger(0);

	private boolean finishedAddingJobs = false;
	private int remainingJobsCount = 0;

	private final Runnable onFinish;

	public JobSynchronizer(Runnable onFinish) {
		this.onFinish = onFinish;
	}

	public synchronized void addJob(String name, final Runnable job) {
		++remainingJobsCount;

		new Thread(name + "-" + threadId.getAndAdd(1)) {
			@Override
			public void run() {
				synchronized (JobSynchronizer.this) {
					try {
						job.run();
					} finally {
						--remainingJobsCount;
						checkFinish();
					}
				}
			}
		}.start();
	}

	public synchronized void finishedAddingJobs() {
		finishedAddingJobs = true;
		checkFinish();
	}

	private void checkFinish() {
		if (finishedAddingJobs && remainingJobsCount == 0) onFinish.run();
	}

}
