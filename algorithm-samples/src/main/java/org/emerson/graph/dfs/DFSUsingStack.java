package org.emerson.graph.dfs;

import java.util.List;
import java.util.Stack;

import org.emerson.graph.Algorithm;
import org.emerson.graph.Graph;
import org.emerson.graph.Node;

//https://java2blog.com/depth-first-search-in-java/
public class DFSUsingStack implements Algorithm {

	// Iterative DFS using stack
	/**
	 * Basic Approach:
	 * 1. Anything on the stack has been visited. (LIFO)
	 * 2. To prime the pump, I get a root node, mark it as visited and put it on the stack.
	 * 3. Then, pop the element off the stack if there is anything
	 * 4. Otherwise,  visit each of its children and push them onto the stack if they have not already been visiited.
	 * 5. Return to 3 unless there is nothing on the staack.
	 */
	@Override
	public void solve(Graph graph, Node end)
	{
		solve(graph, graph.root(), end);
	}
	
	@Override
	public void solve(Graph graph, Node start, Node end)
	{
		Stack<Node> stack=new Stack<Node>();
		stack.add(start);
		start.visit();
		while (!stack.isEmpty())
		{
			Node element=stack.pop();
			System.out.print(element.data() + " ");
 
			List<Node> neighbours=graph.getChildrenForNode(element);
			for (int i = 0; i < neighbours.size(); i++) {
				Node child=neighbours.get(i);
				if(child!=null && !child.isVisited())
				{
					stack.add(child);
					child.visit();
 
				}
			}
		}
	}
}
