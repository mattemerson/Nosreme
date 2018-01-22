package org.nosreme.scheduler.work;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ErrorSummary
{
	public int myCode;
	private String myDescription;
	private List<ErrorDetail> myDetails = new ArrayList<ErrorDetail>();
	
	public ErrorSummary(int code, String description)
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

	public void addDetail(ErrorDetail detail)
	{
		this.myDetails.add(detail);
	}
	
	public List<ErrorDetail> details()
	{
		return Collections.unmodifiableList(myDetails);
	}
}
