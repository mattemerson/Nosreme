package org.nosreme.scheduler.work;

import org.nosreme.scheduler.scheduler.Schedule;
import org.nosreme.scheduler.scheduler.ScheduleRequest;
import org.nosreme.scheduler.state.JobState;
import org.nosreme.scheduler.work.BaseScheduledJob.RunContext;
import org.nosreme.scheduler.work.BaseScheduledJob.RunContextState;
import org.nosreme.scheduler.worker.WorkerPoolManager;

public abstract class BaseScheduledJob implements StatefulJob, ScheduledJob
{	
	protected JobInfo myJobInfo;
	protected Schedule mySchedule;
	protected WorkerPoolManager myWorkerManager;
	
	/**
	 * Constructor
	 * @param jobInfo
	 * @param schedule
	 * @param workerManager
	 * @param runsToKeep
	 */
	protected BaseScheduledJob(JobInfo jobInfo, Schedule schedule, WorkerPoolManager workerManager, int runsToKeep)
	{
		if (jobInfo == null)
		{
			throw new NullPointerException("'jobInfo' is a required parameter");
		}
		
		if (schedule == null)
		{
			throw new NullPointerException("'schedule' is a required parameter");
		}
		
		if (workerManager == null)
		{
			throw new NullPointerException("'workerManager' is a required parameter");
		}
		
		if ( (runsToKeep < 1) || (runsToKeep > 100) )
		{
			throw new IllegalArgumentException("'runsToKeep' must be a value [1,100]");
		}
		
		myJobInfo = jobInfo;
		mySchedule = schedule;
		myWorkerManager = workerManager;
	}
	
	/**
	 * These should all be replaced
	 * @param x
	 */
	protected void log(Exception ex)
	{
		System.out.println(ex.getMessage());
		ex.printStackTrace();		
	}
			
	protected abstract void preRun(RunContext context);
		
	protected abstract void doRun(RunContext runContext);
	
	protected abstract void postRun(RunContext runContext);
	
	@Override
	public ScheduleRequest getNextScheduledRuntime()
	{
		return mySchedule.getNextScheduledRuntime(null); 
	}
	
	public Schedule schedule()
	{
		return mySchedule;
	}
	
	public synchronized JobInfo jobInfo()
	{
		return myJobInfo;
	}
	
	@Override
	public synchronized JobState state()
	{
		return jobInfo().state();
	}
	
	@Override
	public synchronized void fail(ErrorSummary errorSummary)
	{
		JobInfo newJobInfo = jobInfo().fail(errorSummary);
		myJobInfo = newJobInfo;
	}
	
	@Override
	public synchronized void cancel()
	{
		JobInfo newJobInfo = jobInfo().cancel();
		myJobInfo = newJobInfo;
	}
	
	@Override
	public synchronized void complete()
	{
		JobInfo newJobInfo = jobInfo().complete();
		myJobInfo = newJobInfo;
	}
	
	@Override
	public synchronized void submit(ScheduleRequest scheduleRequest)
	{
		JobInfo newJobInfo = jobInfo().submit(scheduleRequest);
		myJobInfo = newJobInfo;
	}
	
	@Override
	public synchronized void activate()
	{
		JobInfo newJobInfo = jobInfo().activate();
		myJobInfo = newJobInfo;
	}
	
	protected class RunContext
	{
		private RunContextState myState;
		private ErrorSummary myErrorSummary;
		
		protected RunContext()
		{
			myState = RunContextState.IN_PROGRESS;
		}
		
		public RunContextState state()
		{
			return myState;
		}
		
		public ErrorSummary errorSummary()
		{
			return myErrorSummary;
		}
				
		public void fail(ErrorSummary errorSummary)
		{
			changeState(RunContextState.FAILED);
			
			if (errorSummary == null)
			{
				throw new NullPointerException("'errorSummary' is a required parameter");
			}
			
			myErrorSummary = errorSummary;
		}
		
		public void success()
		{
			changeState(RunContextState.SUCCESS);			
		}
		
		private void changeState(RunContextState state)
		{
			if (state == null)
			{
				throw new NullPointerException("'state' is a required parameter");
			}
			
			if (RunContextState.IN_PROGRESS != myState)
			{
				throw new IllegalStateException("State can only be changed to '" + state + "' if current state is '" + RunContextState.IN_PROGRESS + "', current state='" + myState + "'");
			}
			
			myState = state;
		}
	}
	
	protected enum RunContextState
	{
		FAILED,
		IN_PROGRESS,
		SUCCESS
	}
}
