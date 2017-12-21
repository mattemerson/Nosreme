package biz.matt.scheduler.scheduler;

import java.time.ZonedDateTime;

public class ScheduleRequest
{
	private JobPriority myPriority;
	private ZonedDateTime myScheduledTime;
	
	
	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("<schedule_request>").append("\n");
		builder.append("<priority>").append(priority()).append("</priority>").append("\n");
		builder.append("<scheduled_time>").append(scheduledTime()).append("</scheduled_time>").append("\n");		
		builder.append("</schedule_request>");
		return builder.toString();
	}
	
	
	public ScheduleRequest(JobPriority priority, ZonedDateTime scheduledTime)
	{
		if (priority == null)
		{
			throw new NullPointerException("'priority' is a required parameter");
		}
		
		if (scheduledTime == null)
		{
			throw new NullPointerException("'scheduledTime' is a required parameter");
		}
		
		myPriority = priority;
		myScheduledTime = scheduledTime;
	}
	
	public JobPriority priority()
	{
		return myPriority;
	}

	public ZonedDateTime scheduledTime()
	{
		return myScheduledTime;
	}
}
