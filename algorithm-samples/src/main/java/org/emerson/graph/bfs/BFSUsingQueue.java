package org.emerson.graph.bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.emerson.graph.Algorithm;
import org.emerson.graph.Graph;
import org.emerson.graph.Node;

//https://java2blog.com/breadth-first-search-in-java/
public class BFSUsingQueue implements Algorithm {

	@Override
	public void solve(Graph graph, Node end) {
		
		solve(graph, graph.root(), end);		
	}

	@Override
	public void solve(Graph graph, Node start, Node end)
	{
		Queue<Node> queue = new LinkedList<Node>();
		start.visit();
		queue.add(start);
		
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
