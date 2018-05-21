package org.nosreme.util.entity;

public class IntegerKeyedEntity implements IntegerKeyed
{
	private Integer id;
	
	@Override
	public Integer getID()
	{
		return id;
	}

	@Override
	public void setID(Integer id)
	{
		this.id = id;		
	}
}
