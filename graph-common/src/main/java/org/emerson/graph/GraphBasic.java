package org.emerson.graph;

import java.util.List;
import java.util.Set;

public class GraphBasic implements Graph
{
	private Edges edges;
	private List<Node> node;
	
	private Set<GraphNode> nodes;
	
	public GraphBasic(Set<GraphNode> nodes)
	{
		this.nodes = nodes;
	}
	
	@Override
	public GraphNode getNode(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addNode(GraphNode from, GraphNode to, int weight) {
		// TODO Auto-generated method stub		
	}

	@Override
	public void addNode(GraphNode from, GraphNode to) {
		// TODO Auto-generated method stub
		
	}
}
