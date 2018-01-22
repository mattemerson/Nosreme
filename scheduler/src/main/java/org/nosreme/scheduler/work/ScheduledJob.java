package org.nosreme.scheduler.work;

import org.nosreme.scheduler.scheduler.ScheduleRequest;

/**
 * A Scheduled Job
 * @author memerson
 *
 */
public interface ScheduledJob extends Runnable
{
	ScheduleRequest getNextScheduledRuntime();
}
