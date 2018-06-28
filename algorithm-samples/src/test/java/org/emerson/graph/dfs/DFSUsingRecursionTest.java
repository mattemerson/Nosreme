package org.emerson.graph.dfs;

import org.emerson.graph.Algorithm;
import org.emerson.graph.Graph;
import org.emerson.graph.GraphTestHelper;
import org.emerson.graph.Node;
import org.emerson.graph.dfs.DFSUsingRecursion;
import org.junit.Test;

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