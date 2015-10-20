package com.github.awvalenti.bauhinia.forficata;

class JobSynchronizer {

	private volatile boolean noMoreJobs = false;
	private volatile int jobCount = 0;

	private final Runnable onFinish;

	public JobSynchronizer(Runnable onFinish) {
		this.onFinish = onFinish;
	}

	public synchronized void newJob(final Runnable job) {
		++jobCount;

		new Thread() {
			{
				setDaemon(false);
			}

			@Override
			public void run() {
				try {
					job.run();
				} finally {
					finishedJob();
				}
			}
		}.start();
	}

	private synchronized void finishedJob() {
		--jobCount;
		checkFinish();
	}

	public synchronized void noMoreJobs() {
		noMoreJobs = true;
		checkFinish();
	}

	private void checkFinish() {
		if (noMoreJobs && jobCount == 0) onFinish.run();
	}

}
