package org.nosreme.scheduler;

import org.nosreme.scheduler.scheduler.JobScheduleConfig;
import org.nosreme.scheduler.scheduler.JobScheduleFactory;
import org.nosreme.scheduler.scheduler.PeriodicSchedule;
import org.nosreme.scheduler.scheduler.Schedule;
import org.nosreme.scheduler.scheduler.ScheduleFactory;
import org.nosreme.scheduler.scheduler.ScheduleOption;
import org.nosreme.scheduler.scheduler.SingleRunSchedule;
import org.nosreme.scheduler.work.PeriodicScheduledJob;
import org.nosreme.scheduler.work.ScheduledJob;
import org.nosreme.scheduler.work.ScheduledJobFactory;
import org.nosreme.scheduler.work.ScheduledRunnable;
import org.nosreme.scheduler.work.SingleScheduledJob;
import org.nosreme.scheduler.worker.WorkerPoolManager;

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
