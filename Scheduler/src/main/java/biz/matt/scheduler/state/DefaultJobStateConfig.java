package biz.matt.scheduler.state;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class DefaultJobStateConfig implements JobStateConfig
{
	/**
	 * 
	 */
	private Map<JobState,Set<JobState>> myAcceptableStateTransitions;
	
	public DefaultJobStateConfig()
	{
	}

	{
		Map<JobState,Set<JobState>> acceptableStateTransitions = new EnumMap<JobState, Set<JobState>>(JobState.class);
						
		acceptableStateTransitions.put(JobState.UNSUBMITTED, Collections.emptySet());
		acceptableStateTransitions.put(JobState.SUBMITTED, EnumSet.of(JobState.UNSUBMITTED));
		acceptableStateTransitions.put(JobState.ACTIVE, EnumSet.of(JobState.SUBMITTED));
		acceptableStateTransitions.put(JobState.COMPLETED, EnumSet.of(JobState.ACTIVE));
		acceptableStateTransitions.put(JobState.FAILED, EnumSet.of(JobState.SUBMITTED, JobState.ACTIVE));
		acceptableStateTransitions.put(JobState.CANCELLED, EnumSet.of(JobState.UNSUBMITTED, JobState.SUBMITTED));
		
		myAcceptableStateTransitions = acceptableStateTransitions;
	}

	@Override
	public boolean isValidTransition(JobState fromState, JobState toState)
	{
		// Required parameter
		if (fromState == null)
		{
			throw new NullPointerException("'fromState' is a required parameter.");
		}
		
		// Required parameter
		if (toState == null)
		{
			throw new NullPointerException("'toState' is a required parameter.");
		}
		
		// Every states should registered for each state even if there are no valid fromStates.
		Set<JobState> validFromStates = myAcceptableStateTransitions.get(toState);
		if (validFromStates == null)
		{
			throw new IllegalStateException("There are no valid from state transitions for toState='" + toState + "'");
		}
		
		// Returns true or false if this is a valid state transitions.
		boolean isValid = validFromStates.contains(fromState);
		
		return isValid;
	}

}
