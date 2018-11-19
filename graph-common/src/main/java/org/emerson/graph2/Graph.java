package org.emerson.graph2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph
{
	private List<Node> nodes;
	private Edges edges;
	private Map<String,Node> nodesByID;
	
	public Graph(List<Node> nodes, Edges edges)
	{
		this.nodes = nodes;
		this.edges = edges;
		
		nodesByID = new HashMap<>();
		for (Node node : nodes)
		{
			nodesByID.put(node.getID(), node);
		}
	}
	
	public List<Node> getNodes()
	{
		return this.nodes;
	}
	
	public Edges getEdges()
	{
		return this.edges;
	}
	
	public void depthFirstSearch()
	{
		List<Node> path = null;
	}
	
	public void breadthFirstSearch()
	{
		List<Node> path = null;
	}
	
	public void djikstrasAlgorithm()
	{
		List<List<Node>> nodes;
	}
}
