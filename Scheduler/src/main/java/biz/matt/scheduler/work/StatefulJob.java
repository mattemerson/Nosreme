package biz.matt.scheduler.work;

import java.time.ZonedDateTime;

import biz.matt.scheduler.scheduler.ScheduleRequest;
import biz.matt.scheduler.state.JobState;

public interface StatefulJob
{
	/**
	 * Returns the state of this job
	 * @return
	 */
	JobState state();
	
	/**
	 * Sets this job to the <code>JobState.FAILED</code> state
	 * @param errorSummary
	 */
	void fail(ErrorSummary errorSummary);	
	
	/**
	 * Sets this job to the <code>JobState.CANCELLED</code> state
	 */
    void cancel();	
    
    /**
     * Sets this job to the <code>JobState.COMPLETED</code> state
     */
    void complete();	
    
    /**
     * Sets this job to the <code>JobState.SUBMITTED</code> state
     */
	void submit(ScheduleRequest scheduleRequest);

	/**
	 * Sets this job to the <code>JobState.ACTIVE</code> state
	 */
	void activate();
}
