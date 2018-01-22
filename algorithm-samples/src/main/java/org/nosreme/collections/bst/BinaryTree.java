package org.nosreme.collections.bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTree
{
	private BinaryNode myRoot;
	
	/**
	 * Constructor
	 * @param root required
	 */
	public BinaryTree(BinaryNode root)
	{
		if (root == null)
		{
			throw new IllegalArgumentException("'root' is a required parameter");
		}
		
		myRoot = root;
	}

	public BinaryTree()
	{
		// empty tree
	}
	
	public BinaryNode root()
	{
		return myRoot;
	}
	
	
	public void insert(int value)
	{
		BinaryNode node = new BinaryNode(value);
		
		insert(node);
	}
	
	public void insert(BinaryNode node)
	{
		if (node == null)
		{
			throw new IllegalArgumentException("'node' is a required parameter.");
		}
		
		BinaryNode root = root();
		if (root == null)
		{
			myRoot = node;
		}
		else
		{
			root.insert(node);
		}
	}
	
	public List<Integer> toOrderedList()
	{
		if (root() == null)
		{
			return Collections.emptyList();
		}
		else
		{
			List<Integer> list = new ArrayList<Integer>();
			return root().toOrderedList(list);
		}
	}
}