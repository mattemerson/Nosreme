package org.nosreme.scheduler.work;

public class ErrorDetail
{
	public int myCode;
	private String myDescription;
	
	public ErrorDetail(int code, String description)
	{
		myCode = code;
		myDescription = description;
	}
	
	public int code()
	{
		return myCode;
	}
	
	public String description()
	{
		return myDescription;
	}
}
