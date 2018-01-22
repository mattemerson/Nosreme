package org.nosreme.scheduler.work;

import biz.matt.scheduler2.scheduler.JobScheduleConfig;
import biz.matt.scheduler2.scheduler.PeriodicSchedule;
import biz.matt.scheduler2.scheduler.Schedule;
import biz.matt.scheduler2.scheduler.ScheduleOption;
import biz.matt.scheduler2.scheduler.SingleRunSchedule;
import biz.matt.scheduler2.state.JobStateConfig;
import biz.matt.scheduler2.time.SystemTime;
import biz.matt.scheduler2.worker.WorkerPoolManager;

public class ScheduledJobFactory
{
	private JobStateConfig myJobStateConfig;
	private SystemTime mySystemTime;
	
	public ScheduledJobFactory(JobStateConfig jobStateConfig, SystemTime systemTime, WorkerPoolManager workerManager)
	{
		if (jobStateConfig == null)
		{
			throw new NullPointerException("'jobStateConfig' is a required parameter");
		}
		
		if (systemTime == null)
		{
			throw new NullPointerException("'systemTime' is a required parameter");
		}
		
		if (workerManager == null)
		{
			throw new NullPointerException("'workerManager' is a required parameter.");
		}
		
		myJobStateConfig = jobStateConfig;
		mySystemTime = systemTime;
		myWorkerManager = workerManager;
	}

	public SingleScheduledJob newScheduledJob(Runnable singleRunSchedule, Schedule singletonSchedule)
	{
		SingleScheduledJob job = myJobFactory.newSingleScheduledJob(singleRunSchedule, singletonSchedule);
		
		return job;
	}
		
	public PeriodicScheduledJob newPeriodicScheduledJob(ScheduledRunnable scheduledRunnable, Schedule periodicSchedule)
	{
		PeriodicScheduledJob job = myJobFactory.newPeriodicScheduledJob(scheduledRunnable, periodicSchedule);
		
		return job;
	}
	
	public SingleScheduledJob newSingleScheduledJob(Runnable runnable)
	{
		if (runnable == null)
		{
			throw new NullPointerException("'runnable' is a required parameter.");
		}
		
		//Bind with a schedule and submit to the workermanager
		JobScheduleConfig config = new JobScheduleConfig();
		config.setScheduleOption(ScheduleOption.RUN_ONLY_ONCE_ON_SUBMISSION);
		
		SingleScheduledJob job = newScheduledJob(Runnable singleRunnable, Schedule singletonSchedule);
		
		return job;
		
		submit(runnable, config);
	}
	
	public void submit(Runnable runnable, JobScheduleConfig singletonScheduleConfig)
	{
		if (runnable == null)
		{
			throw new NullPointerException("'runnable' is a required parameter.");
		}
		
		if (singletonScheduleConfig == null)
		{
			throw new NullPointerException("'singletonSchedule' is a required parameter.");
		}
		
		//Bind into a scheduledjob and submit to the workermanager
		Schedule schedule = this.myScheduleFactory.getSchedule(singletonScheduleConfig);
		
		SingleScheduledJob scheduledJob = newScheduledJob(runnable, schedule);
		
		submit(scheduledJob);
	}
		
	public void submit(ScheduledRunnable scheduledRunnable, JobScheduleConfig jobScheduleConfig)
	{
		if (scheduledRunnable == null)
		{
			throw new NullPointerException("'scheduledRunnable' is a required parameter.");
		}
		
		if (jobScheduleConfig == null)
		{
			throw new NullPointerException("'jobScheduleConfig' is a required parameter.");
		}		
		
		Schedule periodicSchedule = myScheduleFactory.getSchedule(jobScheduleConfig);
		
		//Wrap into a scheduledperiodicjob, bind the period schedule, and submit to the workermanager
		ScheduledJob scheduledJob = newPeriodicScheduledJob(scheduledRunnable, periodicSchedule);
		
		submit(scheduledJob);
	}
	
	/**
	 * Returns an instance of a job that will only run once: immediately or at a specified time
	 * 
	 * @param singleRunnable
	 * @param singleRunSchedule
	 * @param workerManager
	 * @return
	 */
	public SingleScheduledJob newSingleScheduledJob(Runnable singleRunnable, Schedule singleRunSchedule)
	{
		if (singleRunnable == null)
		{
			throw new NullPointerException("'singleRunnable' is a required parameter.");
		}
		
		if (singleRunSchedule == null)
		{
			throw new NullPointerException("'singletonSchedule' is a required parameter.");
		}
				
		JobInfo jobInfo = JobInfo.newInstance(myJobStateConfig, mySystemTime);
		
		SingleScheduledJob job = new SingleScheduledJob(jobInfo, singleRunnable, singleRunSchedule, myWorkerManager);
		
		return job;
	}
		
	/**
	 * Returns an instance of a job that will run periodically
	 * 
	 * @param scheduledRunnable
	 * @param periodicSchedule
	 * @param workerManager
	 * @return
	 */
	public PeriodicScheduledJob newPeriodicScheduledJob(ScheduledRunnable scheduledRunnable, Schedule periodicSchedule)
	{
		if (scheduledRunnable == null)
		{
			throw new NullPointerException("'scheduledRunnable' is a required parameter.");
		}
		
		if (periodicSchedule == null)
		{
			throw new NullPointerException("'periodicSchedule' is a required parameter.");
		}
				
		JobInfo jobInfo = JobInfo.newInstance(myJobStateConfig, mySystemTime);
		
		PeriodicScheduledJob job = new PeriodicScheduledJob(jobInfo, scheduledRunnable, periodicSchedule, myWorkerManager);
		
		return job;
	}

}
