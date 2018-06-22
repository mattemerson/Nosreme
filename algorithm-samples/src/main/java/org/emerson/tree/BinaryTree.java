package org.emerson.tree;

import java.util.Objects;
import java.util.function.Function;

public class BinaryTree {

	private BinaryNode root;
		
	/**
	 * We'll allow no initial node
	 */
	public BinaryTree()
	{		
	}
	
	/**
	 * Pass in an initial node
	 * @param root
	 */
	public BinaryTree(BinaryNode root)
	{
		this.root = root;
	}
	
	/**
	 * Does a binary search to add a node to the tree
	 * @param node
	 */
	public void addNode(BinaryNode node)
	{
		Objects.requireNonNull(node, "'node' is a required input");
		
		if (root == null)
		{
			root = node;
		}
		else
		{
			addNode(root, node);
		}
	}
	
	/**
	 * Uses a binary search to recursively add a node to the tree...could use a stack to
	 * @param nodeToAddTo
	 * @param nodeToAdd
	 */
	private void addNode(BinaryNode nodeToAddTo, BinaryNode nodeToAdd)
	{		
		int parentValue = nodeToAddTo.getValue();
		int nodeValue = nodeToAdd.getValue();
		
		// Consider the left side
		if (nodeValue < parentValue)
		{
			BinaryNode leftNode = nodeToAddTo.getLeft();
			
			if (Objects.isNull(leftNode))
			{
				nodeToAddTo.setLeft(nodeToAdd);
			}
			else
			{
				addNode(leftNode, nodeToAdd);
			}
		}
		// Consider the right side
		else if (nodeValue > parentValue)
		{
			BinaryNode rightNode = nodeToAddTo.getRight();
			
			if (Objects.isNull(rightNode))
			{
				nodeToAddTo.setRight(nodeToAdd);
			}
			else
			{
				addNode(rightNode, nodeToAdd);
			}			
		}
		// Increment our count in case we care about duplicates
		else
		{
			nodeToAddTo.incrementFrequency();
		}
	}
	
	/**
	 * Finds sums in the tree
	 * @param nodeSummer
	 * @return
	 */
	public IntSolution findSum(TreeMaxSumFinder nodeSummer)
	{
		return nodeSummer.findMaxSum(root);
	}
}
