package biz.matt.scheduler.scheduler;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.ZonedDateTime;

import biz.matt.scheduler.PeriodicTask.RunOptions;
import biz.matt.scheduler.time.Quarter;

public class JobScheduleConfig
{
	private ScheduleOption myScheduleOption;
	private JobPriority myPriority;
	private ZonedDateTime myStartScheduledDateTime;
	private ZonedDateTime myEndScheduledDateTime;
	private UseHistoryForScheduling myUseHistoryForScheduling;
	
	private Duration myPeriod;
	private Integer myMaxIterations;
	private LocalDate myLocalDate;
	private LocalTime myLocalTime;
	private Year myYear;
	private DayOfWeek myDayOfWeek;
	private Month myMonth;
	private Quarter myQuarter;
	
	// In case my child has a different Priority than me
	private JobPriority myChildPriority;
	private ZonedDateTime myScheduledTime;
	
	/**
	 * Constructor
	 */
	public JobScheduleConfig()
	{
	}
			
	public ScheduleOption getScheduleOption()
	{
		return myScheduleOption;
	}

	public void setScheduleOption(ScheduleOption myScheduleOption)
	{
		this.myScheduleOption = myScheduleOption;
	}

	public JobPriority getPriority()
	{
		return myPriority;
	}

	public void setPriority(JobPriority myPriority)
	{
		this.myPriority = myPriority;
	}

	public ZonedDateTime getStartScheduledDateTime()
	{
		return myStartScheduledDateTime;
	}

	public void setStartScheduledDateTime(ZonedDateTime myStartScheduledDateTime)
	{
		this.myStartScheduledDateTime = myStartScheduledDateTime;
	}

	public ZonedDateTime getEndScheduledDateTime()
	{
		return myEndScheduledDateTime;
	}

	public void setEndScheduledDateTime(ZonedDateTime myEndScheduledDateTime)
	{
		this.myEndScheduledDateTime = myEndScheduledDateTime;
	}

	public UseHistoryForScheduling getUseHistoryForScheduling()
	{
		return myUseHistoryForScheduling;
	}

	public void setUseHistoryForScheduling(UseHistoryForScheduling myUseHistoryForScheduling)
	{
		this.myUseHistoryForScheduling = myUseHistoryForScheduling;
	}

	public Duration getPeriod()
	{
		return myPeriod;
	}

	public void setPeriod(Duration myPeriod)
	{
		this.myPeriod = myPeriod;
	}

	public LocalDate getLocalDate()
	{
		return myLocalDate;
	}

	public void setLocalDate(LocalDate myLocalDate)
	{
		this.myLocalDate = myLocalDate;
	}

	public LocalTime getLocalTime()
	{
		return myLocalTime;
	}

	public void setLocalTime(LocalTime myLocalTime)
	{
		this.myLocalTime = myLocalTime;
	}

	public Year getYear()
	{
		return myYear;
	}

	public void setYear(Year myYear)
	{
		this.myYear = myYear;
	}

	public DayOfWeek getDayOfWeek()
	{
		return myDayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek myDayOfWeek)
	{
		this.myDayOfWeek = myDayOfWeek;
	}

	public Month getMonth()
	{
		return myMonth;
	}

	public void setMonth(Month myMonth)
	{
		this.myMonth = myMonth;
	}

	public Quarter getQuarter()
	{
		return myQuarter;
	}

	public void setQuarter(Quarter myQuarter)
	{
		this.myQuarter = myQuarter;
	}

	public ZonedDateTime getScheduledTime()
	{
		return myScheduledTime;
	}

	public void setScheduledTime(ZonedDateTime myScheduledTime)
	{
		this.myScheduledTime = myScheduledTime;
	}
	
	public void setMaxIterations(Integer maxIterations)
	{
		this.myMaxIterations = maxIterations;
	}
	
	public Integer getMaxIterations()
	{
		return myMaxIterations;
	}
	
	public void setChildPriority(JobPriority priority)
	{
		this.myChildPriority = priority;
	}
	
	public JobPriority getChildPriority()
	{
		return this.myChildPriority;
	}
}
