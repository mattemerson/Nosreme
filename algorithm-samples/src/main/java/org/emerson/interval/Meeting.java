package org.emerson.interval;

import java.time.LocalDateTime;

public class Meeting
{
	private LocalDateTime startTime;
	private LocalDateTime endTime;
	
	public Meeting(LocalDateTime start, LocalDateTime end)
	{
		this.startTime = start;
		this.endTime = end;
	}
	
	public LocalDateTime getStartTime()
	{
		return this.startTime;
	}
			
	public LocalDateTime getEndTime()
	{
		return this.endTime;
	}
}
