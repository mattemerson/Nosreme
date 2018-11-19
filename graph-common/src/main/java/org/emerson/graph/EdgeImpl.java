package org.emerson.graph;

public class EdgeImpl implements Edge
{
	private static final int DEFAULT_EDGE_WEIGHT = 1;
	
	private int weight;
	private GraphNode node;
	
	public EdgeImpl(GraphNode node)
	{
		this(node, DEFAULT_EDGE_WEIGHT);
	}
	
	public EdgeImpl(GraphNode node, int weight)
	{
		this.node = node;
		this.weight = weight;
	}
	
	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public GraphNode getEndNode() {
		return node;
	}

}
