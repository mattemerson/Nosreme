package biz.matt.scheduler.worker;

import java.time.Duration;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import biz.matt.scheduler.scheduler.JobPriority;
import biz.matt.scheduler.scheduler.ScheduleRequest;
import biz.matt.scheduler.scheduler.SchedulingFailedException;
import biz.matt.scheduler.work.SingleScheduledJob;

public class WorkerPoolManager 
{
	private Map<JobPriority,WorkerPool> myWorkerPools = new HashMap<JobPriority,WorkerPool>();
	
	private WorkerPoolManager()
	{		
	}

	public static WorkerPoolManager newWorkQueueManager(WorkerPoolManagerConfiguration configuration)
	{
		WorkerPoolManager workerPoolManager = new WorkerPoolManager();
		
		Collection<WorkerPoolConfiguration> poolConfigs = configuration.workerPoolConfigurations();
		for(WorkerPoolConfiguration poolConfig : poolConfigs)
		{
			WorkerPool workerPool = WorkerPool.newWorkerPool(poolConfig);
			workerPoolManager.registerWorkerPool(workerPool);
		}
				
		return workerPoolManager;
	}
	
	private void registerWorkerPool(WorkerPool workerPool)
	{
		if (myWorkerPools.containsKey(workerPool.priority()))
		{
			throw new IllegalStateException("pool with priority='" + workerPool.priority()+ "' has already been registered.");
		}
		
		myWorkerPools.put(workerPool.priority(), workerPool);
	}
	
	public void stopPools()
	{
		for (WorkerPool workerPool : myWorkerPools.values())
		{
			try
			{
				workerPool.stopPool();
			}
			catch(Exception ex)
			{
				System.out.println("Failure shutting down pool priority='" + workerPool.priority() + "'.  Continuing...");//, ex);
			}
		}
	}
		
	public void submit(SingleScheduledJob scheduledJob)
	{
		if (scheduledJob == null)
		{
			throw new NullPointerException("'scheduledJob' is a required parameter.");
		}
		
		ScheduleRequest scheduleRequest = scheduledJob.getNextScheduledRuntime();
		
		JobPriority jobPriority = scheduleRequest.priority();
		WorkerPool workerPool = myWorkerPools.get(jobPriority);
		if (workerPool == null)
		{
			throw new SchedulingFailedException("work could not be scheduled because priority='" + jobPriority + "' is not a recognized priority.");
		}		
		
		workerPool.submit(scheduledJob, scheduleRequest);
	}
}
