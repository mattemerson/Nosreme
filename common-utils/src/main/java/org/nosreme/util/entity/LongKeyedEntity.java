package org.nosreme.util.entity;

public class LongKeyedEntity implements LongKeyed
{
	private Long id;
	
	@Override
	public Long getID()
	{
		return id;
	}

	@Override
	public void setID(Long id)
	{
		this.id = id;		
	}
}
