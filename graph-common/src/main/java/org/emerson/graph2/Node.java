package org.emerson.graph2;

import java.util.List;

public class Node
{
	private int index;
	
	public Node(Node node, int index, List<Edge> edges)
	{
		this.node = node;
		this.index = index;
		this.edges = edges;
	}
}
