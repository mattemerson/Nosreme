package biz.matt.scheduler;

import biz.matt.scheduler.scheduler.JobScheduleConfig;
import biz.matt.scheduler.scheduler.JobScheduleFactory;
import biz.matt.scheduler.scheduler.PeriodicSchedule;
import biz.matt.scheduler.scheduler.Schedule;
import biz.matt.scheduler.scheduler.ScheduleFactory;
import biz.matt.scheduler.scheduler.ScheduleOption;
import biz.matt.scheduler.scheduler.SingleRunSchedule;
import biz.matt.scheduler.work.PeriodicScheduledJob;
import biz.matt.scheduler.work.ScheduledJob;
import biz.matt.scheduler.work.ScheduledJobFactory;
import biz.matt.scheduler.work.ScheduledRunnable;
import biz.matt.scheduler.work.SingleScheduledJob;
import biz.matt.scheduler.worker.WorkerPoolManager;

public class JobScheduler
{
	private WorkerPoolManager myWorkerManager;;
	
	/**
	 * Constructor
	 * 
	 * @param workQueueManager
	 * @param scheduleFactory
	 */
	public JobScheduler(WorkerPoolManager workerManager)
	{
		if (workerManager == null)
		{
			throw new NullPointerException("'workerManager' is a required parameter");
		}
		
		myWorkerManager = workerManager;
	}	
		
	/**
	 * Should wrap the SingletonScheduledJob
	 * @param scheduledJob
	 */
	public void submit(SingleScheduledJob scheduledJob)
	{
		if (scheduledJob == null)
		{
			throw new NullPointerException("scheduledJob' is a required parameter.'");
		}
		
		myWorkerManager.submit(scheduledJob);
	}
}
