package org.nosreme.graph;

import java.util.List;

public interface Graph {
	Node root();
	List<Node> getChildrenForNode(Node node);
	void unvisitAll();
	Node get
}
