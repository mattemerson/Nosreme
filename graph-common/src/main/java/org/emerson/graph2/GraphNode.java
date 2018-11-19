package org.emerson.graph2;

import java.util.List;
import java.util.Objects;

public class GraphNode {

	private GraphData graphData;
	private int index;
	
	public GraphNode(GraphData graphData, int index)
	{
		Objects.requireNonNull(graphData, "'graphData' is a required input");
		if (index < 0)
		{
			throw new IllegalArgumentException("'index' must be non-negative");
		}
		
		this.graphData = graphData;
		this.index = index;
	}
	
	public String getID()
	{
		
	}
	
	public Integer getValue()
	{
		
	}
	
	public List<Edge> getEdges()
	{
		
	}
	
	public List<GraphNode> getNeighbors()
	{
		
	}
}
