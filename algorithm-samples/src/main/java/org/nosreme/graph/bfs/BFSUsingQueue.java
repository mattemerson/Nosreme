package org.nosreme.graph.bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.nosreme.graph.Algorithm;
import org.nosreme.graph.Graph;
import org.nosreme.graph.Node;

//https://java2blog.com/breadth-first-search-in-java/
public class BFSUsingQueue implements Algorithm {

	@Override
	public void solve(Graph graph) {
		
		solve(graph, graph.root());		
	}

	private void solve(Graph graph, Node node)
	{
		Queue<Node> queue = new LinkedList<Node>();
		node.visit();
		queue.add(node);
		
		while(!queue.isEmpty())
		{
			Node current = queue.remove();
			System.out.println(current.data() + " ");
			
			List<Node> children = graph.getChildrenForNode(current);
			for (Node child : children)
			{
				if ((child != null) && (!child.isVisited()))
				{
					child.visit();
					queue.add(child);
				}
			}
		}
		
	}
}
