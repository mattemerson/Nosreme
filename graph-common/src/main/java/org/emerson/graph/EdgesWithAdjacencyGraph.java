package org.emerson.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EdgesWithAdjacencyGraph extends EdgesBase
{
	private List<List<Integer>> adjacencyGraph;
	
	public EdgesWithAdjacencyGraph(int numberOfVertices)
	{
		adjacencyGraph = new ArrayList<List<Integer>>(numberOfVertices);
		for (int ii=0;ii<numberOfVertices;ii++)
		{
			List<Integer> row = new ArrayList<Integer>(numberOfVertices);
			// This row may not work.,...
			Collections.fill(row, 0);
			adjacencyGraph.add(row);			
		}
	}
	
	public void addEdge(Edge edge)
	{
		
	}
	/*
	@Override
	public void addDirectedEdge(Edge edge)
	{
		
	}
	
	@Override
	public void addUndirectedEdge(Edge edge)
	{
		
	}
	
	@Override
	public int getWeight(int origin, int destination)
	{
		
	}
	*/

	@Override
	public void addDirectedEdge(int origin, int destination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUndirectedEdge(int origin, int destination) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getWeight(int origin, int destination) {
		// TODO Auto-generated method stub
		return 0;
	}
}
