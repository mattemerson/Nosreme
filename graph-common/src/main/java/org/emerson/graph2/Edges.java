package org.emerson.graph2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Edges {

	private List<List<Edge>> edges;
	
	
	public Edges(int numberOfNodes)
	{
		if (numberOfNodes < 1)
		{
			throw new IllegalArgumentException("'numberOfNodes' must be greater than 0");
		}
		
		List<List<Edge>> edges = new ArrayList<List<Edge>>(numberOfNodes);
		for (int ii=0;ii<numberOfNodes;ii++)
		{
			edges.add(new LinkedList<Edge>());
		}	
		this.edges = edges;
	}
		
	public void addEdge(int index, Edge edge)
	{
		getEdgesForNode(index),add(edge);
	}
	
	public List<Edge> getEdgesForNode(int index)
	{
		return edges.get(index);
	}
	
	public List<Edge> getEdgesForNode(GraphNode node)
	{
		return getEdgesForNode(node.getIndex());
	}
}
