package com.github.awvalenti.bauhinia.forficata;

class JobSynchronizer {

	private boolean noMoreJobs = false;
	private int jobCount = 0;

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
				synchronized (JobSynchronizer.this) {
					// Runs jobs one at a time

					try {
						job.run();
					} finally {
						--jobCount;
						checkFinish();
					}
				}
			}
		}.start();
	}

	public synchronized void end() {
		noMoreJobs = true;
		checkFinish();
	}

	private void checkFinish() {
		if (noMoreJobs && jobCount == 0) onFinish.run();
	}

}
