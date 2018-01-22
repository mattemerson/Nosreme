package org.nosreme.scheduler.work;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import biz.matt.scheduler2.scheduler.ScheduleRequest;
import biz.matt.scheduler2.state.InvalidJobStateTransition;
import biz.matt.scheduler2.state.JobState;
import biz.matt.scheduler2.state.JobStateConfig;
import biz.matt.scheduler2.state.StateTransition;
import biz.matt.scheduler2.time.SystemTime;

public class JobInfo
{
	private JobStateConfig myJobStateConfig;
	private SystemTime mySystemTime;
	
	private ScheduleRequest myScheduleRequest;
	
	private ZonedDateTime myActualStartTime;
	private ZonedDateTime myActualEndTime;
	
	private JobState myState;
	private List<StateTransition> myStateTransitions = new LinkedList<StateTransition>();

	private AggregateWorkHistory myAllRunHistory;
	private AggregateWorkHistory myFailedRunHistory;
	private AggregateWorkHistory mySuccessfulRunHistory;
	
	/**
	 * Constructor
	 * @param scheduledTime
	 * @param stateConfig
	 * @param systemTime
	 */
	private JobInfo(JobStateConfig stateConfig, SystemTime systemTime, int runHistoryToKeep, int failedHistoryToKeep, int successfulHistoryToKeep)
	{
		this(stateConfig,
			 systemTime,
			 null,
			 null,
			 null,
			 JobState.UNSUBMITTED,
			 new LinkedList<StateTransition>(),
			 new AggregateWorkHistory(runHistoryToKeep),
			 new AggregateWorkHistory(failedHistoryToKeep),
			 new AggregateWorkHistory(successfulHistoryToKeep));
	}

	private JobInfo(JobStateConfig stateConfig,
					SystemTime systemTime,
					ScheduleRequest scheduleRequest,
					ZonedDateTime actualStartTime,
					ZonedDateTime actualEndTime,
					JobState state,
					List<StateTransition> stateTransitions,
					AggregateWorkHistory allRunHistory,
					AggregateWorkHistory failedRunHistory,
					AggregateWorkHistory successfulRunHistory)
	{		
		if (stateConfig == null)
		{
			throw new NullPointerException("'stateConfig' is a required parameter");
		}
				
		if (systemTime == null)
		{
			throw new NullPointerException("'systemTime' is a required parameter");
		}
		
		if (stateTransitions == null)
		{
			throw new NullPointerException("'stateTransitions' is a required parameter");
		}

		if (allRunHistory == null)
		{
			throw new NullPointerException("'allRunHistory' is a required parameter");
		}
		
		if (failedRunHistory == null)
		{
			throw new NullPointerException("'failedRunHistory' is a required paramater");
		}
		
		if (successfulRunHistory == null)
		{
			throw new NullPointerException("'successfulRunHistory' is a required parameter");
		}
		
		myJobStateConfig = stateConfig;
		mySystemTime = systemTime;
		myScheduleRequest = scheduleRequest;
		myActualStartTime = actualStartTime;
		myActualEndTime = actualEndTime;
		myState = state;
		myStateTransitions = stateTransitions;
		myAllRunHistory = allRunHistory;
		myFailedRunHistory = failedRunHistory;
		mySuccessfulRunHistory = successfulRunHistory;

	}	
	
	@Override
	public String toString()
	{
		final StringBuilder builder = new StringBuilder();
		builder.append("<job_info" ).append(" state='" ).append(state()).append("'>").append("\n");
		builder.append(myScheduleRequest).append("\n");
		builder.append("<actual_start_time>").append(myActualStartTime).append("</actual_start_time>").append("\n");
		builder.append("<actual_end_time>").append(myActualEndTime).append("</actual_end_time>").append("\n");		
		builder.append("</job_info>");
		
		return builder.toString();

	}
	/**
	 * Factory method
	 * @param scheduledTime
	 * @param stateConfig
	 * @param systemTime
	 * @return
	 */
	public static JobInfo newInstance(JobStateConfig stateConfig, SystemTime systemTime, int runHistoryToKeep, int failedHistoryToKeep, int successfulHistoryToKeep)
	{
		return new JobInfo(stateConfig, systemTime, runHistoryToKeep, failedHistoryToKeep, successfulHistoryToKeep);
	}
		
	/**
	 * Basic accessors 
	 */
	public AggregateWorkHistory allRunHistory()
	{
		return myAllRunHistory;
	}
	
	public AggregateWorkHistory failedRunHistory()
	{
		return myFailedRunHistory;
	}
	
	public AggregateWorkHistory successfulRuns()
	{
		return mySuccessfulRunHistory;
	}
	
	public ScheduleRequest scheduleRequest()
	{
		return myScheduleRequest;
	}
	
	public JobState state()
	{
		return myState;
	}
	
	public ZonedDateTime actualStartTime()
	{
		return myActualStartTime;
	}

	public ZonedDateTime actualEndTime()
	{
		return myActualEndTime;
	}
	
	public List<StateTransition> stateTransitions()
	{
		return Collections.unmodifiableList(myStateTransitions);
	}
			
	public Duration duration()
	{
		if (!state().isTerminal())
		{
			throw new IllegalStateException("Duration cannot be calculated because job is not in a terminal state.");
		}
		
		return Duration.between(actualStartTime(), actualEndTime());
	}	
	
	/**
	 * State Mutators
	 */
	
	
	public JobInfo fail(ErrorSummary errorSummary)
	{
		return changeState(JobState.FAILED, null, mySystemTime.now(), errorSummary);
	}
	
	public JobInfo cancel()
	{
		return changeState(JobState.CANCELLED, null, mySystemTime.now(), null);
	}
	
	public JobInfo complete()
	{
		return changeState(JobState.COMPLETED, null, mySystemTime.now(), null);
	}
	
	public JobInfo submit(ScheduleRequest scheduleRequest)
	{
		return changeState(JobState.SUBMITTED, scheduleRequest, mySystemTime.now(), null);
	}
	
	public JobInfo activate()
	{
		return changeState(JobState.ACTIVE, null, mySystemTime.now(), null);
	}
				
	/**
	 * Changes the state of the job
	 * @param toState
	 * @param changeTime
	 * @param errorSummary
	 */
	public JobInfo changeState(JobState toState, ScheduleRequest scheduleRequest, ZonedDateTime changeTime, ErrorSummary errorSummary)
	{
		if (toState == null)
		{
			throw new NullPointerException("'state' is a required parameter");
		}
		
		if (changeTime == null)
		{
			throw new  NullPointerException("'changeTime' is a required parameter");
		}
		
		if ( (toState == JobState.SUBMITTED) &&
		     (scheduleRequest == null) )
		 {
			throw new NullPointerException("'scheduleRequest' is a required parameter when target state ='" + JobState.SUBMITTED + "'");
		 }
			
		JobState currentState = state();
		if (!myJobStateConfig.isValidTransition(currentState, toState))
		{
			throw new InvalidJobStateTransition("Invalid state transition: from:'"+ currentState + " to:'" + toState + "'");
		}
			
		StateTransition stateTransition = new StateTransition(currentState, toState, changeTime, errorSummary);
			
		List<StateTransition> newStateTransitions = new LinkedList<StateTransition>(stateTransitions());
		newStateTransitions.add(stateTransition);
			
		scheduleRequest = (toState == JobState.SUBMITTED) ? scheduleRequest : this.scheduleRequest();
		ZonedDateTime actualStartTime = (toState == JobState.ACTIVE) ? changeTime : this.actualStartTime();
		ZonedDateTime actualEndTime = (toState.isTerminal()) ? changeTime : this.actualEndTime();
					
		JobInfo newJobInfo = new JobInfo(myJobStateConfig,
										 mySystemTime,
										 scheduleRequest,
										 actualStartTime,
										 actualEndTime,
										 toState,
										 newStateTransitions,
										 myAllRunHistory,
										 myFailedRunHistory,
										 mySuccessfulRunHistory);

			return newJobInfo;			
		}
	}
}
