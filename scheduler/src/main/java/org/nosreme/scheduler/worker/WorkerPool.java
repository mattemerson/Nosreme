package org.nosreme.scheduler.worker;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.nosreme.scheduler.scheduler.JobPriority;
import org.nosreme.scheduler.scheduler.ScheduleRequest;
import org.nosreme.scheduler.time.SystemTime;
import org.nosreme.scheduler.work.SingleScheduledJob;

public class WorkerPool
{
	private SystemTime mySystemTime;
	private boolean myStarted;
	private JobPriority myPriority;
	private int myNumberOfWorkers;
	private ScheduledExecutorService myService;
	
	/**
	 * Constructor
	 * 
	 * @param priority
	 * @param numberOfWorkers
	 * @param clockForScheduling
	 */
	private WorkerPool(JobPriority priority, int numberOfWorkers, SystemTime systemTime)
	{
		myPriority = priority;
		myNumberOfWorkers = numberOfWorkers;
		mySystemTime = systemTime;
	}
	
	/**
	 * <code>WorkerPool</code> factory method
	 * @param configuration
	 * @return
	 */
	public static WorkerPool newWorkerPool(WorkerPoolConfiguration configuration)
	{
		if (configuration == null)
		{
			throw new NullPointerException("'configuration' is a required parameter");
		}
		
		WorkerPool workerPool = new WorkerPool(configuration.priority(), configuration.numberOfWorkerThreads(), configuration.systemTime());
		
		workerPool.startPool();

		return workerPool;
	}
	
	/**
	 * This pushes a new ScheduledJob into the worker pools for execution.
	 * 
	 * @param scheduledJob
	 */
	public void submit(SingleScheduledJob scheduledJob, ScheduleRequest scheduleRequest)
	{		
		ZonedDateTime nextScheduledRuntime = scheduleRequest.scheduledTime();
						
		Duration duration = Duration.between(mySystemTime.now(), nextScheduledRuntime);
		long delay = duration.getSeconds();
		
		scheduledJob.submit(scheduleRequest);
		
		myService.schedule(scheduledJob, delay, TimeUnit.SECONDS);
	}
	
	private synchronized void startPool()
	{
		myService = Executors.newScheduledThreadPool(numberOfWorkers());
		
		myStarted = true;
	}
	
	public synchronized void stopPool()
	{
		// If not started, our work here is done;
		if (myStarted)
		{
			return;
		}
		
		try
		{
			myService.shutdown();
			myService.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (InterruptedException e)
		{
		    System.err.println("tasks interrupted");
		}
		finally
		{
		    if (!myService.isTerminated())
		    {
		        System.err.println("cancel non-finished tasks");
		    }
		    myService.shutdownNow();
		    System.out.println("shutdown finished");
		    
		    // Not sure if you're guaranteed to get here....
		    myStarted = false;
		}
	}
	
	public JobPriority priority()
	{
		return myPriority;
	}
	
	public int numberOfWorkers()
	{
		return myNumberOfWorkers;
	}
}
