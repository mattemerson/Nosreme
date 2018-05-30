package org.nosreme.graph.dfs;

import org.nosreme.graph.Algorithm;
import org.nosreme.graph.Graph;
import org.nosreme.graph.Node;

//https://java2blog.com/depth-first-search-in-java/
public class DFSUsingRecursion implements Algorithm {

	@Override
	public void solve(Graph graph, Node end)
	{
		solve(graph, graph.root(), end);
	}
	
	@Override
	public void solve(Graph graph, Node start, Node end) {

		System.out.print(start.data() + " ");
		start.visit();
		for (Node child : graph.getChildrenForNode(start))
		{
			if ( (child != null) && (!child.isVisited()))
			{
				solve(graph, child, end);
			}
		}
		
	}

}
