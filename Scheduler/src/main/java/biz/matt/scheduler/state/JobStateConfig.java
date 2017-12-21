package biz.matt.scheduler.state;

public interface JobStateConfig
{
	/**
	 * Returns true if this is a valid state transition, false otherwise
	 * @param fromState
	 * @param toState
	 * @return true if valid transition, false otherwise
	 */
	boolean isValidTransition(JobState fromState, JobState toState);
}
