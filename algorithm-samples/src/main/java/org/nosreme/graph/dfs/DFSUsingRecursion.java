package org.nosreme.graph.dfs;

import org.nosreme.graph.Algorithm;
import org.nosreme.graph.Graph;
import org.nosreme.graph.Node;

//https://java2blog.com/depth-first-search-in-java/
public class DFSUsingRecursion implements Algorithm {

	@Override
	public void solve(Graph graph)
	{
		solve(graph, graph.root());
	}
	
	private void solve(Graph graph, Node node) {

		System.out.print(node.data() + " ");
		node.visit();
		for (Node child : graph.getChildrenForNode(node))
		{
			if ( (child != null) && (!child.isVisited()))
			{
				solve(graph, child);
			}
		}
		
	}

}
