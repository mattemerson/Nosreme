package org.nosreme.graph;

import java.util.Arrays;

public class GraphTestHelper {
	
	public static Graph getGraphWithChildrenOnNodes()
	{
		Node node40 =new Node(40);
		Node node10 =new Node(10);
		Node node20 =new Node(20);
		Node node30 =new Node(30);
		Node node60 =new Node(60);
		Node node50 =new Node(50);
		Node node70 =new Node(70);
 
		node40.addNeighbour(node10);
		node40.addNeighbour(node20);
		node10.addNeighbour(node30);
		node20.addNeighbour(node10);
		node20.addNeighbour(node30);
		node20.addNeighbour(node60);
		node20.addNeighbour(node50);
		node30.addNeighbour(node60);
		node60.addNeighbour(node70);
		node50.addNeighbour(node70);
		
		Graph graph = new GraphWithChildrenOnNodes(node40, Arrays.asList(node40, node10, node20, node30, node60,node50,node70));
		return graph;
	}
	
	public static Graph getGraphWithChildrenOnAdjacencyGraph()
	{
		Node node40 =new Node(40);
		Node node10 =new Node(10);
		Node node20 =new Node(20);
		Node node30 =new Node(30);
		Node node60 =new Node(60);
		Node node50 =new Node(50);
		Node node70 =new Node(70);
 		
		int adjacencyMatrix[][] = {
				{0,1,1,0,0,0,0},  // Node 1: 40
				{0,0,0,1,0,0,0},  // Node 2 :10
				{0,1,0,1,1,1,0},  // Node 3: 20
				{0,0,0,0,1,0,0},  // Node 4: 30
				{0,0,0,0,0,0,1},  // Node 5: 60
				{0,0,0,0,0,0,1},  // Node 6: 50
				{0,0,0,0,0,0,0},  // Node 7: 70
		};
		
		Graph graph = new GraphWithChildrenOnAdjacencyMatrix(node40, Arrays.asList(node40, node10, node20, node30, node60,node50,node70), adjacencyMatrix);
		return graph;
	}	
}
