package biz.matt.scheduler.scheduler;

public interface ScheduleFactory
{
	ScheduleOption scheduleOption();
	
	Schedule newSchedule(JobScheduleConfig config);
}
