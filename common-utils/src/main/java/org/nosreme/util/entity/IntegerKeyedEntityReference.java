package org.nosreme.util.entity;

public class IntegerKeyedEntityReference extends IntegerKeyedEntity implements IntegerKeyedReference
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
