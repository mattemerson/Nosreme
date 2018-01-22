package org.nosreme.scheduler.state;

import java.time.ZonedDateTime;

import org.nosreme.scheduler.work.ErrorSummary;

public class StateTransition
{
	private JobState myFromState;
	private JobState myState;
	private ZonedDateTime myTransitionTime;
	private ErrorSummary myErrorSummary;
	
	public StateTransition(JobState fromState, JobState toState, ZonedDateTime transitionTime, ErrorSummary errorSummary)
	{
		if (fromState == null)
		{
			throw new NullPointerException("'fromState' is a required parameter.");
		}
		
		if (toState == null)
		{
			throw new NullPointerException("'toState' is a required parameter.");
		}	
				
		if (transitionTime == null)
		{
			throw new NullPointerException("transitionTime' is a required parameter");
		}
		
		myFromState = fromState;
		myState = toState;
		myTransitionTime = transitionTime;
		myErrorSummary = errorSummary;
	}

	public JobState fromState()
	{
		return myFromState;
	}
	
	public JobState state()
	{
		return myState;
	}
		
	public ZonedDateTime transitionTime()
	{
		return myTransitionTime;
	}
	
	public ErrorSummary errorSummary()
	{
		return myErrorSummary;
	}
}
