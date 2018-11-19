package org.emerson.graph2;

public class EdgeDefn {

	private String origin;
	private String destination;
	private Direction direction;
	private int weight;
	
	public EdgeDefn(String origin, String destination, Direction direction, int weight)
	{
		this.origin = origin;
		this.destination = destination;
		this.direction = direction;
		this.weight = weight;
	}
	
	public String getOrigin()
	{
		return this.origin;
	}
	
	public String getDestination()
	{
		return this.destination;
	}
	
	public Direction getDirection()
	{
		return this.direction;
	}
	
	public int getWeight()
	{
		return this.weight;
	}
}
