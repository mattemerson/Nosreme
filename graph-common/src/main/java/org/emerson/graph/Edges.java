package org.emerson.graph;

public interface Edges {

	boolean isDirected();
	void addDirectedEdge(int origin, int destination);	
	void addUndirectedEdge(int origin, int destination);	
	int getWeight(int origin, int destination);
}
