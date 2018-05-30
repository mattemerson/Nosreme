package org.nosreme.graph.bfs;

import org.junit.Test;
import org.nosreme.graph.Algorithm;
import org.nosreme.graph.Graph;
import org.nosreme.graph.GraphTestHelper;
import org.nosreme.graph.Node;

public class BFSUsingQueueTest {
	
	@Test
	public void testBFSUsingRecursionShouldPass()
	{
		Algorithm bfsAlgorithm = new BFSUsingQueue();

		Node start = new Node(40);
		Node end = new Node(70);
		
		System.out.println("The DFS traversal of the graph using queue ");
		Graph graph = GraphTestHelper.getGraphWithChildrenOnNodes();
		bfsAlgorithm.solve(graph, start, end);
		System.out.println("\n");
		
		graph = GraphTestHelper.getGraphWithChildrenOnAdjacencyGraph();
		bfsAlgorithm.solve(graph, start, end);		
		
	}
}
