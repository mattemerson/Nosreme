package org.nosreme.graph.dfs;

import org.junit.Test;
import org.nosreme.graph.Algorithm;
import org.nosreme.graph.Graph;
import org.nosreme.graph.GraphHelper;

public class DFSUsingStackTest {

	@Test
	public void testDFSUsingStackShouldPass()
	{
		Algorithm dfsAlgorithm = new DFSUsingStack();
 
		System.out.println("The DFS traversal of the graph using stack ");
		Graph graph = GraphHelper.getGraphWithChildrenOnNodes();
		dfsAlgorithm.solve(graph);
		System.out.println("\n");
		
		graph = GraphHelper.getGraphWithChildrenOnAdjacencyGraph();
		dfsAlgorithm.solve(graph);	
	}
}
