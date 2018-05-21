package org.nosreme.util.entity;

public class StringKeyedEntityReference extends StringKeyedEntity implements StringKeyedReference
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
