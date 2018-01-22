package org.nosreme.scheduler.scheduler;

public interface ScheduleFactory
{
	ScheduleOption scheduleOption();
	
	Schedule newSchedule(JobScheduleConfig config);
}
