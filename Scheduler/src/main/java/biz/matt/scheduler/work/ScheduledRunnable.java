package biz.matt.scheduler.work;

import java.time.ZonedDateTime;

public interface ScheduledRunnable
{
	/**
	 * Factory method to create a new runnable
	 * @param stats
	 * @return
	 */
	public Runnable newRunnable(ZonedDateTime scheduledTime);
}
