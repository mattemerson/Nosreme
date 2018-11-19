package org.emerson.graph2;

import java.util.List;

public class GraphDefn {
	private List<EdgeDefn> edges;
	private List<Node> nodes; 
	
	public GraphDefn(List<Node> nodes, List<EdgeDefn> edges)
	{
		this.nodes = nodes;
		this.edges = edges;
	}
	
	public List<Node> getNodes()
	{
		return this.nodes;
	}
	
	public List<EdgeDefn> getEdges()
	{
		return this.edges;
	}
}
