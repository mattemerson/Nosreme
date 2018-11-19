package org.emerson.graph;

public abstract class EdgesBase implements Edges
{
	private boolean directed;
	
	protected EdgesBase()
	{
		this.directed = false;
	}
	
	public boolean isDirected()
	{
		return this.directed;
	}
	
	protected void setDirected(boolean directed)
	{
		this.directed = directed;
	}
}
