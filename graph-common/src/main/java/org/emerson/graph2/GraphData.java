package org.emerson.graph2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GraphData
{
	private Map<String,NodeDefn> nodesByID;
	private List<NodeDefn> nodes;
	private List<List<Edge>> edges;
	
	public GraphData()
	{
		nodesByID = new HashMap<>();
		nodes = new ArrayList<>();
		edges = new ArrayList<List<Edge>>();
	}
	
	public void addNode(NodeDefn nodeDefn)
	{
		Objects.requireNonNull(nodeDefn, "'nodeDefn' is a required parameter");

		if (nodesByID.containsKey(nodeDefn.getID()))
		{
			throw new IllegalStateException("node id='" + nodeDefn.getID() + "' already exists in this graph");
		}
		
		nodesByID.put(nodeDefn.getID(), nodeDefn);
		nodes.add(nodeDefn);
		edges.add(new LinkedList<Edge>());
	}
}
