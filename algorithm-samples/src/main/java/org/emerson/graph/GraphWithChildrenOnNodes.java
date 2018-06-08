package org.emerson.graph;

import java.util.List;

public class GraphWithChildrenOnNodes implements Graph {
	private Node root;
	private List<Node> nodes;
	
	public GraphWithChildrenOnNodes(Node root, List<Node> nodes)
	{
		this.root = root;
		this.nodes = nodes;
	}
	
	@Override
	public Node root()
	{
		return root;
	}
	
	@Override
	public void unvisitAll()
	{
		for (Node node : nodes)
		{
			node.unvisit();
		}
	}

	@Override
	public List<Node> getChildrenForNode(Node node) {
		return node.getNeighbours();
	}
}
