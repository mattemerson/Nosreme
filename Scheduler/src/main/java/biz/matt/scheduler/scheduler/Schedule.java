package biz.matt.scheduler.scheduler;

import java.time.ZonedDateTime;

public interface Schedule
{
	ScheduleRequest getNextScheduledRuntime(ZonedDateTime lastScheduledRuntime);
	
	void waitUntilScheduledTime(ZonedDateTime scheduledRuntime);
}
