package org.emerson.interval;

public class IntMeeting
{
	private int start;
	private int end;
	
	public IntMeeting(int start, int end)
	{
		this.start= start;
		this.end = end;
	}
	
	public int getStart()
	{
		return this.start;
	}
	
	public int getEnd()
	{
		return this.end;
	}
	
	public boolean overlapsWith(IntMeeting otherMeeting)
	{
		// Rule: a meeting is inclusive of its start date and exclusive of its end time
		if ( (otherMeeting.getStart() < this.getEnd()) &&
		     (otherMeeting.getEnd() > this.getStart()) )
		{
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString()
	{
		return "(" + start + "," + end + ")";
	}
}
