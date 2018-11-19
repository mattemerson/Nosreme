package org.emerson.graph2;

public class NodeDefn
{
	private String id;
	private Integer value;
	
	public NodeDefn(String id, Integer value)
	{
		this.id = id;
		this.value = value;
	}
	
	public String getID() {
		return this.id;
	}

	public Integer getValue() {
		return this.value;
	}
	
}
