package org.emerson.zaius.bst;

import java.util.LinkedList;

public class BinaryTree implements Visitee
{
	private BinaryNode root;
	
	public BinaryTree()
	{		
	}
	
	public BinaryNode getRoot()
	{
		return this.root;
	}
	
	// Add a new node; using Breadth First Insert
	public void addNode(int value)
	{		
		// If root is null, just create the root
		if (root == null)
		{
			this.root = newBinaryNode(value);
			return;
		}
		
		// Otherwise....
		LinkedList<BinaryNode> queue = new LinkedList<BinaryNode>();
		queue.add(root);
		while (!queue.isEmpty())
		{
			BinaryNode current = queue.pop();
			
			// Start on the left; add it to the queue if it's already there
			BinaryNode left = current.getLeftChild();			
			if (left != null)
			{
				queue.add(left);
			}
			else
			{
				current.setLeftChild(newBinaryNode(value));
				return;
			}
			
			// The go right, add it to the queue if it's already they
			BinaryNode right = current.getRightChild();
			if (right != null)
			{
				queue.add(right);
			}
			else
			{
				current.setRightChild(newBinaryNode(value));
				return;
			}						
		}
				
	}
	
	/**
	 * Simple factory method
	 * @param value
	 * @return
	 */
	private BinaryNode newBinaryNode(int value)
	{
		return new BinaryNode(value);
	}
	
	@Override
	public String toString()
	{
		StringBuilder record = new StringBuilder();
		NodeVisitor visitor = new InOrderVisitor(record);
		visitor.visit(root);
		
		return record.toString();
	}
			
	/**
	 * Not technically a visitor pattern because we're not walking a heterogeneous hierarchy, but it will get the job done
	 * @author memerson
	 *
	 */
	private class InOrderVisitor extends NodeVisitor
	{
		private StringBuilder record;
		
		public InOrderVisitor(StringBuilder builder)
		{
			record = builder;
		}
		
		public void visit(BinaryNode node)
		{
			if (node != null)
			{
				record.append(node.getValue()).append(",");
				visit(node.getLeftChild());
				visit(node.getRightChild());			
			}
		}
	}
	
	private abstract class NodeVisitor
	{
		public abstract void visit(BinaryNode node);
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visit(this);		
	}
}
