package org.nosreme.scheduler.work;

import java.time.Duration;
import java.time.Period;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class AggregateWorkHistory
{		
	private int myRunsToKeep;
	private int myRunCount = 0;
	private Deque<JobInfo> myHistory;
	private Duration myMinRunTime = Duration.ofSeconds(0);
	private Duration myMaxRunTime = Duration.ofSeconds(0);
	
	public AggregateWorkHistory(int runsToKeep)
	{
		if ( (runsToKeep < 1) || (runsToKeep > 100) )
		{
			throw new IllegalArgumentException("runsToKeep must be set to [1,100]");
		}
		
		myRunsToKeep = runsToKeep;
		myHistory = new ArrayDeque<JobInfo>(runsToKeep);
	}
	
	public int runsToKeep()
	{
		return myRunsToKeep;
	}
	
	public int runCount()
	{
		return myRunCount;
	}
	
	public Deque<JobInfo> history()
	{
		return myHistory;
	}
	
	public Duration minRunTime()
	{
		return myMinRunTime;
	}
	
	public Duration maxRunTime()
	{
		return myMaxRunTime;
	}
	
	public void aggregateWorkStats(JobInfo jobInfo)
	{
		if (jobInfo == null)
		{
			throw new NullPointerException("'workHistory' is a required input");
		}
		
		myRunCount++;
							
		Duration jobDuration = jobInfo.duration();
		if (myMinRunTime.compareTo(jobDuration) > 0)
			myMinRunTime = jobDuration;
		
		
		if (myMaxRunTime.compareTo(jobDuration) < 0)
			myMaxRunTime = jobDuration;
		
		myHistory.addFirst(jobInfo);
		if (myHistory.size() > myRunsToKeep)
			myHistory.removeLast();
	}
}
