package com.github.awvalenti.bauhinia.coronata;

class JobSynchronizer {

	private boolean finishedAddingJobs = false;
	private int remainingJobsCount = 0;

	private final Runnable onFinish;

	public JobSynchronizer(Runnable onFinish) {
		this.onFinish = onFinish;
	}

	public synchronized void addJob(final Runnable job) {
		++remainingJobsCount;

		new Thread() {
			{
				// Necessary because parent thread is daemon. If we do not set
				// this thread as daemon=false, Java program may terminate
				// before actually finished.
				setDaemon(false);
			}

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
