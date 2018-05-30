package org.nosreme.graph.dfs;

import org.junit.Test;
import org.nosreme.graph.Algorithm;
import org.nosreme.graph.Graph;
import org.nosreme.graph.GraphTestHelper;
import org.nosreme.graph.Node;

public class DFSUsingRecursionTest {

	@Test
	public void testDFSUsingRecursionShouldPass()
	{
		Node start = new Node(40);
		Node end = new Node(70);
		
		Algorithm dfsAlgorithm = new DFSUsingRecursion();
 
		System.out.println("The DFS traversal of the graph using stack ");
		Graph graph = GraphTestHelper.getGraphWithChildrenOnNodes();
		dfsAlgorithm.solve(graph, start, end);
		System.out.println("\n");
		
		graph = GraphTestHelper.getGraphWithChildrenOnAdjacencyGraph();
		dfsAlgorithm.solve(graph, start, end);		
		
	}
}