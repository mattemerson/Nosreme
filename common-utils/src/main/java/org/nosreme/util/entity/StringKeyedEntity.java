package org.nosreme.util.entity;

public class StringKeyedEntity implements StringKeyed
{
	private String id;
	
	@Override
	public String getID()
	{
		return id;
	}

	@Override
	public void setID(String id)
	{
		this.id = id;		
	}
}
