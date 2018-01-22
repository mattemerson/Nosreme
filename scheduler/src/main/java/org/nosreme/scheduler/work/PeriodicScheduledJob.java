package org.nosreme.scheduler.work;

import java.time.ZonedDateTime;

import biz.matt.scheduler2.scheduler.Schedule;
import biz.matt.scheduler2.state.JobState;
import biz.matt.scheduler2.work.BaseScheduledJob.RunContext;
import biz.matt.scheduler2.work.BaseScheduledJob.RunContextState;
import biz.matt.scheduler2.worker.WorkerPoolManager;

public class PeriodicScheduledJob extends BaseScheduledJob implements ScheduledJob
{
	private ScheduledRunnable myScheduledRunnable;
	
	/**
	 * Constructor
	 * 
	 * @param scheduledRunnable
	 * @param periodicSchedule
	 */
	PeriodicScheduledJob(JobInfo jobInfo, ScheduledRunnable scheduledRunnable, Schedule periodicSchedule, WorkerPoolManager workerManager)
	{
		super(jobInfo, periodicSchedule, workerManager);
		
		if (scheduledRunnable == null)
		{
			throw new NullPointerException("'scheduledRunnable' is a required parameter.");
		}
				
		myScheduledRunnable = scheduledRunnable;
	}

	@Override
	protected void preRun(RunContext context)
	{
		JobState currentState = jobInfo().state();
		
		// Can only process jobs in the SUBMITTED state
		if (JobState.SUBMITTED == currentState)
		{
			// Move into the active state and start processing
			activate();
		}
		else if (JobState.ACTIVE == currentState)
		{
			// If not keep processing, fail/die!  You could be in this state in a periodic job			
		}
		else
		{
			throw new TerminalJobException("Job could not be activated because its state='" + currentState + "' not '" + JobState.SUBMITTED + "'");
		}
	}
		
	/**
	 * Run the business logic
	 * @param runContext
	 */
	@Override
	protected void doRun(RunContext runContext)
	{
		// If I'm here, then I have another iteration
		if (runContext.state() == RunContextState.IN_PROGRESS)
		{
			// The basic assumption here is that the child will share the same scheduledTime as this iteration of the periodic job
			// If not, we assume that it will be a function of the scheduledTime and the ScheduledRunnable factory will encapsulate
			// the logic			
			ZonedDateTime childScheduledTime = jobInfo().scheduleRequest().scheduledTime();
			
			// Should be the schedule time for this job
			Runnable bizLogicForThisIteration = myScheduledRunnable.newRunnable(childScheduledTime);
						
			this.myWorkerManager.submit(scheduledJob);
			
			// Should build a schedule for this iteration
			workManager().submit(bizLogicForThisIteration);
			
			// Can be repeatedly successful
			runContext.success();
		}
	}
	
	@Override
	protected void postRun(RunContext runContext)
	{
		if (RunContextState.FAILED == runContext.state())
		{
			fail(runContext.errorSummary());
		}
		else if (RunContextState.SUCCESS == runContext.state())
		{
			if (noMoreIterations)
			{
				complete();				
			}
			
			submit()
		}
		else
		{
			throw new IllegalStateException("RunContextState='" + runContext.state() + "' is not allowed in the postRun");
		}
	}
}
