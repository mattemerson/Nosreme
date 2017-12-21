package biz.matt.scheduler.state;

public enum JobState
{
	UNSUBMITTED(false),
	SUBMITTED(false),
	ACTIVE(false),
	COMPLETED(true),
	FAILED(true),
	CANCELLED(true);
	
	JobState(boolean terminal)
	{
		myIsTerminal = terminal;
	}
	
	private boolean myIsTerminal;
	
	public boolean isTerminal()
	{
		return myIsTerminal;
	}
}
