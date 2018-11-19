package org.emerson.graph;

import java.util.Map;
import java.util.Set;

public class GraphNodeBasic implements GraphNode
{
	private int index;
	private String id;
	private Node node;
	private Set<GraphNode> neighbors;
	private Set<Edge> edges;
	private boolean visited;
	
	public GraphNodeBasic(Node node, int index)
	{
		this.node = node;
		this.index = index;
	}
	
	public int getIndex()
	{
		return this.index;
	}
	
	@Override
	public String getId() {
		return id;
	}

	@Override
	public int getValue() {
		return getNode().getValue();
	}

	private Node getNode()
	{
		return this.node;
	}
	
	@Override
	public Set<GraphNode> getNeighbors()
	{
		return this.neighbors;
	}

	@Override
	public void setVisited(boolean visited) {
		this.visited = visited;
		
	}

	@Override
	public boolean isVisited() {
		return this.visited;
	}

	@Override
	public void addNeighbor(GraphNode node) {
		
		neighbors.add(node);
		edges.add(new EdgeImpl(node));		
	}

	@Override
	public void addNeighbor(GraphNode node, int weight)
	{
		neighbors.add(node);
		edges.add(new EdgeImpl(node, weight));
	}
}

