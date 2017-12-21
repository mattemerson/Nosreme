package biz.matt.scheduler.work;

import biz.matt.scheduler.scheduler.Schedule;
import biz.matt.scheduler.state.JobState;
import biz.matt.scheduler.worker.WorkerPoolManager;

public class SingleScheduledJob extends BaseScheduledJob
{
	private Runnable mySingleRunnable;
	
	/**
	 * Constructor
	 * 
	 * @param jobInfo
	 * @param singleRunnable
	 * @param singleRunSchedule
	 * @param workerManager
	 */
	public SingleScheduledJob(JobInfo jobInfo, Runnable singleRunnable, Schedule singleRunSchedule, WorkerPoolManager workerManager)
	{		
		super(jobInfo, singleRunSchedule, workerManager, 1);
		
		//SingleRunnable is a required parameter
		if (singleRunnable == null)
		{
			throw new NullPointerException("'singleRunnable' is a required parameter");
		}
		
		mySingleRunnable = singleRunnable;
	}
			
	@Override
	public void run()
	{
		RunContext runContext = new RunContext();
		try
		{
			System.out.println(jobInfo());
			System.out.println("pre-run");
			preRun(runContext);

			System.out.println(jobInfo());
			
			System.out.println("do-run");
			doRun(runContext);
			
			System.out.println(jobInfo());
		}
		catch(TerminalJobException tje)
		{
			// log and post-run
			log(tje);
			runContext.fail(tje.getErrorSummaryRequired());
		}
		catch(JobExecutionException jee)
		{
			// log and post-run
			log(jee);
			runContext.fail(jee.getErrorSummaryRequired());
		}
		catch(Exception ex)
		{
			// log and post-run
			log(ex);
			runContext.fail(ErrorConstants.UNKNOWN_JOB_ERROR_SUMMARY);
		}
		finally
		{
			try
			{
				System.out.println("post-run");
				postRun(runContext);
				
				System.out.println(jobInfo());
			}
			catch(Exception ex)
			{
				log(ex);
			}
		}
	}
		
	@Override
	protected void preRun(RunContext context)
	{
		JobState currentState = jobInfo().state();
		
		// Can only process jobs in the SUBMITTED state
		if (JobState.SUBMITTED == currentState)
		{
			// We may wake up early, if timing is important, wait until we get to our scheduled time
			schedule().waitUntilScheduledTime(jobInfo().scheduleRequest().scheduledTime());

			// Move into the active state and start processing
			activate();
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
		if (runContext.state() == RunContextState.IN_PROGRESS)
		{
			mySingleRunnable.run();
			
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
			complete();
		}
		else
		{
			throw new IllegalStateException("RunContextState='" + runContext.state() + "' is not allowed in the postRun");
		}
	}
}
