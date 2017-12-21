package biz.matt.scheduler.worker;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import biz.matt.scheduler.scheduler.JobPriority;

public class WorkerPoolManagerConfiguration
{
	private Map<JobPriority,WorkerPoolConfiguration> myWorkerPools = new HashMap<JobPriority,WorkerPoolConfiguration>();
	
	public WorkerPoolManagerConfiguration()
	{
	}

	public void addWorkPool(WorkerPoolConfiguration configuration)
	{		
		if (configuration == null)
		{
			throw new NullPointerException("'configuration' is a required parameter");
		}
	
		JobPriority priority = configuration.priority();
		if (myWorkerPools.containsKey(priority))
		{
			throw new IllegalArgumentException("'priority'='" + priority + "' has already been defined for this WorkerPoolManagerConfiguration.");
		}
		
		myWorkerPools.put(priority, configuration);
	}
	
	public Collection<WorkerPoolConfiguration> workerPoolConfigurations()
	{
		return myWorkerPools.values();
	}
}
