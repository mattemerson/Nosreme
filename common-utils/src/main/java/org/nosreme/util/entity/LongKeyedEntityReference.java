package org.nosreme.util.entity;

public class LongKeyedEntityReference extends LongKeyedEntity implements LongKeyedReference
{
	private String detail;
	
	@Override
	public String getDetail()
	{
		return detail;
	}
	
	@Override
	public void setDetail(String detail)
	{
		this.detail = detail;
	}
}
