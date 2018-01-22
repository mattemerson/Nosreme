package org.nosreme.scheduler.scheduler;

public enum UseHistoryForScheduling
{
	USE_HISTORY(false),
	USE_HISTORY_BUT_SKIP_THE_PAST(true),
	DONT_USE_HISTORY(true);
	
	UseHistoryForScheduling(boolean skipThePast)
	{
		mySkipThePast = skipThePast;
	}
	
	private boolean mySkipThePast;
	
	public boolean skipThePast()
	{
		return this.mySkipThePast;
	}
}