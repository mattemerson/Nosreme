package org.nosreme.graph;

public interface Algorithm {

	/**
	 * Assumes that we want to start at the root of the graph
	 * @param graph
	 * @param end
	 */
	void solve(Graph graph, Node end);
	
	/**
	 * No assumption of starting position
	 * @param graph
	 * @param start
	 * @param end
	 */
	void solve(Graph graph, Node start, Node end);
}
