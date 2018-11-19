package org.emerson.graph;

import java.util.Set;

public interface GraphNode
{
	String getId();
	int getValue();
	Set<GraphNode> getNeighbors();
	void setVisited(boolean visited);
	boolean isVisited();
	void addNeighbor(GraphNode node);
	void addNeighbor(GraphNode node, int weight);
}
