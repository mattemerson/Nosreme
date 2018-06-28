package org.emerson.graph;

import java.util.ArrayList;
import java.util.List;

public class GraphWithChildrenOnAdjacencyMatrix implements Graph
{
	private Node root;
	private List<Node> nodes;
	private int[][] adjacencyMatrix;
	
	public GraphWithChildrenOnAdjacencyMatrix(Node root, List<Node> nodes, int[][] adjacencyMatrix)
	{
		this.root = root;
		this.nodes = nodes;
		this.adjacencyMatrix = adjacencyMatrix;
	}
	
	/**
	 * Convenience method to allow us to get into the search graph.  In practice, you would have the node itself.
	 * @param nonGraphNode
	 * @return
	 */
	//@Override
	/*
	public Node getSearchNode(Node nonGraphNode)
	{
		// This won't work
		Node node;
		for (Node node1 : nodes)
		{
			if (node.equals(nonGraphNode))
			{
				return node;
			}
		}
		return node;
	}
	*/
	
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

	// find neighbors of node using adjacency matrix
	// if adjacency_matrix[i][j]==1, then nodes at index i and index j are connected
	
	@Override
	public List<Node> getChildrenForNode(Node node) {

		int nodeIndex=-1;
 
		// Find my node in the list
		List<Node> neighbours=new ArrayList<>();
		for (int i = 0; i < nodes.size(); i++) {
			if(nodes.get(i).equals(node))
			{
				nodeIndex=i;
				break;
			}
		}
 
		// Using that node
		if(nodeIndex!=-1)
		{
			for (int j = 0; j < adjacencyMatrix[nodeIndex].length; j++) {
				if(adjacencyMatrix[nodeIndex][j]==1)
				{
					neighbours.add(nodes.get(j));
				}
			}
		}
		return neighbours;
	}
}
