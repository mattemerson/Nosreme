package org.emerson.graph2;

public class Edge {

	private int destination;
	private int weight;
	
	public Edge(int destination)
	{
		this.destination = destination;
	}
	
	public Edge(int destination, int weight)
	{
		this(destination);
		this.weight = weight;
	}
	
	public int getDestination()
	{
		return this.destination;
	}
	
	public int getWeight()
	{
		return this.weight;
	}
}
