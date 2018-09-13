package org.emerson.shortestpath;

import java.util.Set;

public interface Node
{
	Integer getValue();
	Set<Node> getNeighbors();
	boolean isVisited();
	void visited();
}
