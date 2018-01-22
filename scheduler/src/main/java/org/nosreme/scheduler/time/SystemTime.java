package org.nosreme.scheduler.time;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class SystemTime
{
	private Clock myClock;
	
	public SystemTime(Clock clock)
	{
		if (clock == null)
		{
			throw new NullPointerException("'clock' is a required parameter");
		}
		
		myClock = clock;
	}
	
	public static SystemTime newInstance()
	{
		return newInstance(Clock.systemUTC());
	}
	
	public static SystemTime newInstance(Clock clock)
	{
		if (clock == null)
		{
			throw new NullPointerException("'clock' is a required parameter");
		}
		
		SystemTime systemTime = new SystemTime(clock);
		
		return systemTime;
	}
		
	public SystemTime with(String timeZoneName)
	{
		if (timeZoneName == null)
		{
			throw new NullPointerException("'timeZoneName' is a required parameter.");
		}
		
		ZoneId zoneId = ZoneId.of(timeZoneName);
		if (zoneId == null)
		{
			throw new NullPointerException("No ZoneId found for timeZoneName='" + timeZoneName + "'");
		}
		
		return with(zoneId);
	}
	
	public SystemTime with(ZoneId zoneId)
	{
		if (zoneId == null)
		{
			throw new NullPointerException("'zoneId' is a required parameter.");
		}
		
		Clock clock = myClock.withZone(zoneId);
		
		return newInstance(clock);
	}
		
	public ZoneId zoneId()
	{
		return myClock.getZone();
	}
	
	public Clock clock()
	{
		return myClock;
	}
	
	public ZonedDateTime now()
	{
		return ZonedDateTime.now(myClock);		
	}
}
