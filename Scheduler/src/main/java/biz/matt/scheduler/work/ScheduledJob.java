package biz.matt.scheduler.work;

import biz.matt.scheduler.scheduler.ScheduleRequest;

/**
 * A Scheduled Job
 * @author memerson
 *
 */
public interface ScheduledJob extends Runnable
{
	ScheduleRequest getNextScheduledRuntime();
}
