package org.nosreme.scheduler.scheduler;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.temporal.TemporalUnit;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.nosreme.scheduler.PeriodicTask.RunOptions;
import org.nosreme.scheduler.time.SystemTime;

public class JobScheduleFactory
{
	Map<ScheduleOption,ScheduleFactory> myScheduleFactories = new EnumMap<ScheduleOption,ScheduleFactory>(ScheduleOption.class);
	private ScheduleFactory myDefaultSingleRunScheduleFactory;
	private ScheduleFactory myDefaultRunAtScheduledTimeFactory;
	private SystemTime mySystemTime;
	
	/**
	 * No-op Constructor
	 */
	public JobScheduleFactory(SystemTime systemTime)
	{
		if (systemTime == null)
		{
			throw new NullPointerException("'systemTime' is a required parameter");
		}
		
		mySystemTime = systemTime;
		
		registerScheduleFactory(new RunImmediatelyScheduleFactory(ScheduleOption.RUN_ONLY_ONCE_ON_SUBMISSION, mySystemTime));
		registerScheduleFactory(new RunAtScheduledTimeScheduleFactory(ScheduleOption.RUN_ONLY_ONCE_AT_SCHEDULED_TIME, systemTime));
		registerScheduleFactory(new RunWithFixedRateScheduleFactory(ScheduleOption.RUN_WITH_FIXED_RATE, systemTime));
		/*
		myScheduleOptions.put(ScheduleOptions.RUN_WITH_FIXED_RATE, workScheduler);
		myScheduleOptions.put(ScheduleOptions.RUN_EVERY_HOUR, workScheduler);
		myScheduleOptions.put(ScheduleOptions.RUN_EVERY_DAY_AT_TIME, workScheduler);
		myScheduleOptions.put(ScheduleOptions.RUN_EVERY_WEEK_ON_DAY_AT_TIME, workScheduler);
		myScheduleOptions.put(ScheduleOptions.RUN_EVERY_WEEK_ON_X_DAY_AT_TIME, workScheduler);
		myScheduleOptions.put(ScheduleOptions.RUN_EVERY_MONTH_ON_FIRST_DAY_OF_TYPE_AT_TIME, workScheduler);
		myScheduleOptions.put(ScheduleOptions.RUN_EVERY_MONTH_ON_X_DAY_AT_TIME, workScheduler);
		myScheduleOptions.put(ScheduleOptions.RUN_EVERY_QUARTER_ON_FIRST_DAY_OF_TYPE_AT_TIME, workScheduler);
		myScheduleOptions.put(ScheduleOptions.RUN_EVERY_QUARTER_ON_X_DAY_AT_TIME, workScheduler);
		myScheduleOptions.put(ScheduleOptions.RUN_EVERY_YEAR_ON_FIRST_DAY_OF_TYPE_AT_TIME, workScheduler);
		myScheduleOptions.put(ScheduleOptions.RUN_EVERY_YEAR_ON_X_DAY_AT_TIME, workScheduler);
		*/
		
		myDefaultSingleRunScheduleFactory = new RunImmediatelyScheduleFactory(ScheduleOption.RUN_ONLY_ONCE_ON_SUBMISSION, mySystemTime);
		myDefaultRunAtScheduledTimeFactory = new RunAtScheduledTimeScheduleFactory(ScheduleOption.RUN_ONLY_ONCE_AT_SCHEDULED_TIME, systemTime);
	}
	
	public ScheduleFactory getDefaultRunImmediatelyScheduleFactory()
	{
		return myDefaultSingleRunScheduleFactory;
	}
	
	public ScheduleFactory getDefaultRunAtScheduledTimeFactory()
	{
		return myDefaultRunAtScheduledTimeFactory;
	}
	
	private void registerScheduleFactory(ScheduleFactory scheduleFactory)
	{
		if (scheduleFactory == null)
		{
			throw new NullPointerException("'scheduleFactory' is a required parameter");
		}
		
		myScheduleFactories.put(scheduleFactory.scheduleOption(), scheduleFactory);
	}
	
	public Schedule getSchedule(JobScheduleConfig config)
	{
		if (config == null)
		{
			throw new NullPointerException("'config' is a required parameter");
		}
		
		ScheduleOption scheduleOption = config.getScheduleOption();
		
		// Get the desired ScheduleFactory
		ScheduleFactory factory = getScheduleFactory(scheduleOption);
		
		Schedule schedule = factory.newSchedule(config);
				
		return  schedule;				
	}
	
	private ScheduleFactory getScheduleFactory(ScheduleOption option)
	{
		if (option == null)
		{
			throw new NullPointerException("'option' is a required parameter");
		}
		
		ScheduleFactory selectedFactory = myScheduleFactories.get(option);
		if (selectedFactory == null)
		{
			throw new IllegalArgumentException("No ScheduleFactory is available for scheduleOption='" + option + "'");
		}
		
		return selectedFactory;
	}
	
	
	public abstract class BaseScheduleFactory implements ScheduleFactory
	{
		private ScheduleOption myScheduleOption;
		private SystemTime mySystemTime;
		
		protected BaseScheduleFactory(ScheduleOption scheduleOption, SystemTime systemTime)
		{
			if (scheduleOption == null)
			{
				throw new NullPointerException("'scheduleOption' is a required parameter");
			}
			
			if (systemTime == null)
			{
				throw new NullPointerException("'systemTime' is a required parameter");
			}
			
			myScheduleOption = scheduleOption;
			mySystemTime = systemTime;
		}
		
		@Override
		public ScheduleOption scheduleOption()
		{
			return myScheduleOption;
		}
		
		public SystemTime systemTime()
		{
			return mySystemTime;
		}
	}
	
	public class RunWithFixedRateScheduleFactory extends BaseScheduleFactory
	{
		public RunWithFixedRateScheduleFactory(ScheduleOption option, SystemTime systemTime)
		{
			super(option, systemTime);
		}
		
		@Override
		public Schedule newSchedule(JobScheduleConfig config)
		{
			return new RunWithFixedRateSchedule(config, mySystemTime);
		}
	}
	
	public class RunImmediatelyScheduleFactory extends BaseScheduleFactory
	{
		public RunImmediatelyScheduleFactory(ScheduleOption option, SystemTime systemTime)
		{
			super(option, systemTime);
		}

		@Override
		public Schedule newSchedule(JobScheduleConfig config)
		{
			return new RunImmediatelySchedule(config, mySystemTime);
		}
		
	}
	
	public class RunAtScheduledTimeScheduleFactory extends BaseScheduleFactory
	{
		public RunAtScheduledTimeScheduleFactory(ScheduleOption option, SystemTime systemTime)
		{
			super(option, systemTime);
		}

		@Override
		public Schedule newSchedule(JobScheduleConfig config)
		{
			return new RunAtSpecifiedTimeSchedule(config, mySystemTime);
		}		
	}
		
	public abstract class BaseSchedule implements Schedule
	{
		private SystemTime mySystemTime;
		private JobScheduleConfig myConfig;
		
		public BaseSchedule(JobScheduleConfig jobScheduleConfig, SystemTime systemTime)
		{
			if (jobScheduleConfig == null)
			{
				throw new NullPointerException("'jobScheduleConfig' is a required parameter");
			}
			
			if (systemTime == null)
			{
				throw new NullPointerException("'systemTime' is a required parameter");
			}
			
			myConfig = jobScheduleConfig;
			mySystemTime = systemTime;			
		}
		
		@Override
		public void waitUntilScheduledTime(ZonedDateTime scheduledRuntime)
		{			
		    Duration duration = Duration.between(scheduledRuntime, mySystemTime.now());
		    while (duration.getSeconds() < 0)
		    {
		    	System.out.println("Woke up early...sleeping for '" + duration.getSeconds() + "' seconds...");
		    	try
		    	{
		    		TimeUnit.SECONDS.sleep(-duration.getSeconds());
		    	}
		    	catch(InterruptedException ie)
		    	{
		    		throw new SchedulingFailedException("Could not wait until scheduled time", ie);
		    	}
		    	
		    	duration = Duration.between(scheduledRuntime, mySystemTime.now());
		    }
		}
	
		protected JobScheduleConfig config()
		{
			return myConfig;
		}
		
		protected SystemTime systemTime()
		{
			return mySystemTime;
		}
		
		protected ScheduleRequest newScheduleRequest(JobPriority priority, ZonedDateTime scheduleTime)
		{
			ScheduleRequest scheduleRequest = new ScheduleRequest(priority, scheduleTime);
			
			return scheduleRequest;
		}

		
	}
	
	public class RunWithFixedRateSchedule extends BaseSchedule implements PeriodicSchedule
	{
		public RunWithFixedRateSchedule(JobScheduleConfig jobScheduleConfig, SystemTime systemTime)
		{
			super(jobScheduleConfig, systemTime);
		}
		
		@Override
		public ScheduleRequest getNextScheduledRuntime(ZonedDateTime lastScheduledRuntime)
		{
			ZonedDateTime nextScheduledRuntime =  mySystemTime.now();
			
			JobPriority priority = JobPriority.NORMAL;
			if (lastScheduledRuntime != null)
			{						
				nextScheduledRuntime = lastScheduledRuntime.plusSeconds(config().getPeriod().getSeconds());
			}
			else
			{
				nextScheduledRuntime = mySystemTime.now();
			}
			
			ScheduleRequest scheduleRequest = newScheduleRequest(priority, nextScheduledRuntime);
			
			return scheduleRequest;
		}
	}
	
	public class RunAtSpecifiedTimeSchedule extends BaseSchedule implements SingleRunSchedule
	{
		RunAtSpecifiedTimeSchedule(JobScheduleConfig config, SystemTime systemTime)
		{
			super(config, systemTime);
		}
		
		@Override
		public ScheduleRequest getNextScheduledRuntime(ZonedDateTime lastScheduledRuntime)
		{
			JobPriority priority = JobPriority.NORMAL;
			//ZonedDateTime scheduleTime = ZonedDateTime.of(2017, 8, 11, 14, 55, 0, 0, systemTime().zoneId());
			ZonedDateTime scheduleTime = config().getScheduledTime();
			
			ScheduleRequest scheduleRequest = newScheduleRequest(priority, scheduleTime);
			
			return scheduleRequest;
		}
	}
	
	public class RunImmediatelySchedule extends BaseSchedule implements  SingleRunSchedule
	{		
		RunImmediatelySchedule(JobScheduleConfig config, SystemTime systemTime)
		{
			super(config, systemTime);
		}
		
		@Override
		public ScheduleRequest getNextScheduledRuntime(ZonedDateTime lastScheduledRuntime)
		{
			JobPriority priority = JobPriority.NORMAL;
			ZonedDateTime scheduleTime = systemTime().now();
			
			ScheduleRequest scheduleRequest = newScheduleRequest(priority, scheduleTime);
			
			return scheduleRequest;
		}
	}
	
	/**
	public class RunOnlyOnceOnSubmission implements WorkScheduler
	{
		@Override
		public JobScheduleConfig getNextScheduledTime(JobScheduleConfig configuration)
		{
			ZonedDateTime endDateTime = configuration.endScheduledDateTime();
			ZonedDateTime startDateTime = configuration.startScheduledDateTime();
			UseHistoryForScheduling useHistory = configuration.useHistoryForScheduling();
			if ((UseHistoryForScheduling.USE_HISTORY==useHistory) || (UseHistoryForScheduling.USE_HISTORY_BUT_SKIP_THE_PAST==useHistory))
			{
				
			}
			
			Instant now = Instant.now();
			
			
			return null;
		}
		*/
	
}
