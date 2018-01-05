package org.nosreme.graph.bfs;

import org.junit.Test;
import org.nosreme.graph.Algorithm;
import org.nosreme.graph.Graph;
import org.nosreme.graph.GraphHelper;

public class BFSUsingQueueTest {
	@Test
	public void testBFSUsingRecursionShouldPass()
	{
		Algorithm bfsAlgorithm = new BFSUsingQueue();
 
		System.out.println("The DFS traversal of the graph using queue ");
		Graph graph = GraphHelper.getGraphWithChildrenOnNodes();
		bfsAlgorithm.solve(graph);
		System.out.println("\n");
		
		graph = GraphHelper.getGraphWithChildrenOnAdjacencyGraph();
		bfsAlgorithm.solve(graph);		
		
	}
}
