package org.emerson.graph.bfs;

import org.emerson.graph.Algorithm;
import org.emerson.graph.Graph;
import org.emerson.graph.GraphTestHelper;
import org.emerson.graph.Node;
import org.emerson.graph.bfs.BFSUsingQueue;
import org.junit.Test;

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
