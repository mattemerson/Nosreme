package org.emerson.graph;

public interface Graph
{
	GraphNode getNode(String id);
	void addNode(GraphNode from, GraphNode to, int weight);
	void addNode(GraphNode from, GraphNode to);
}
