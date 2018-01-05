package org.nosreme.graph;

import java.util.ArrayList;
import java.util.List;

public class Node
{
	private int data;
	private boolean visited;
	private List<Node> neighbours;

	public Node(int data)
	{
		this.data=data;
		this.visited = false;
		this.neighbours=new ArrayList<>();
	}
	
	public int data()
	{
		return data;
	}
	
	public boolean isVisited()
	{
		return visited;
	}
	
	public void visit()
	{
		visited = true;
	}
	
	public void unvisit()
	{
		visited = false;
	}
	
	public void addNeighbour(Node neighbourNode)
	{
		this.neighbours.add(neighbourNode);
	}
	
	public List<Node> getNeighbours() {
		return neighbours;
	}
	public void setNeighbours(List<Node> neighbours) {
		this.neighbours = neighbours;
	}
}