package biz.matt.scheduler.worker;

import java.time.Clock;

import biz.matt.scheduler.scheduler.JobPriority;
import biz.matt.scheduler.time.SystemTime;

public class WorkerPoolConfiguration
{
	private JobPriority myPriority;
	private int myNumberOfWorkerThreads;
	private SystemTime mySystemTime;
		
	public WorkerPoolConfiguration(JobPriority priority, int numberOfThreads, SystemTime systemTime)
	{
		if (priority == null)
		{
			throw new NullPointerException("'priority' is a required parameter.");
		}
		
		if (numberOfThreads < 1)
		{
			throw new IllegalArgumentException("'numberOfThreads='" + numberOfThreads + "', a queue must be assigned at least one thread.");
		}
		
		if (systemTime == null)
		{
			throw new NullPointerException("'systemTime' is a required parameter.");
		}
		
		myPriority = priority;
		myNumberOfWorkerThreads = numberOfThreads;
		mySystemTime = systemTime;
	}
	

	public JobPriority priority()
	{
		return myPriority;
	}
	
	public int numberOfWorkerThreads()
	{
		return myNumberOfWorkerThreads;
	}

	public SystemTime systemTime()
	{
		return this.mySystemTime;
	}
}
