package org.emerson.graph2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GraphFactory
{
	public Graph fromString(String json) throws JsonParseException, JsonMappingException, IOException
	{
		ObjectMapper mapper = new ObjectMapper();
		Graph graph = mapper.readValue(json, Graph.class);
		
		return graph;
	}
	
	public Graph fromGraphDefn(String json) throws JsonParseException, JsonMappingException, IOException
	{
		Objects.requireNonNull(json, "'json' is a required input");
		
		// Read the Graph Configuration from a String
		ObjectMapper mapper = new ObjectMapper();
		GraphDefn graphDefn = mapper.readValue(json, GraphDefn.class);
		if (graphDefn == null)
		{
			throw new IllegalArgumentException("No graph specified in input");
		}
				
		List<Node> nodeDefns = graphDefn.getNodes();
		List<EdgeDefn> edgeDefns = graphDefn.getEdges();
		
		List<Node> graphNodes = new ArrayList<Node>();
		int nodeCount = 0;
		for (Node node : nodeDefns)
		{			
			Node graphNode = new Node(node, nodeCount);

			graphNodes.add(graphNode);
			
			nodeCount++;
		}
		
		Edges edges = new Edges(nodeCount);
		
		for (EdgeDefn edgeDefn : edgeDefns)
		{
			edgeDefn.getDestination();
			edgeDefn.getDirection();
			edgeDefn.getOrigin();
			edgeDefn.getWeight();
		}
				
		Graph graph = new Graph(graphNodes,edges);
		
		return graph;
	}
}
