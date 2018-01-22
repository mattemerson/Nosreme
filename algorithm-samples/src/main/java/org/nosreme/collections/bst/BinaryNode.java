package org.nosreme.collections.bst;

import java.util.List;

public class BinaryNode
{
	private int myCount = 1;
	private int myValue;
	private BinaryNode myLeft;
	private BinaryNode myRight;
	private BinaryNode myParent;
	
	public BinaryNode(int value)
	{
		myValue = value;
	}
	
	public int value()
	{
		return myValue;
	}

	public BinaryNode left()
	{
		return myLeft;
	}
	
	public BinaryNode right()
	{
		return myRight;
	}
	
	public BinaryNode parent()
	{
		return myParent;
	}
	
	public List<Integer> toOrderedList(List<Integer> list)
	{
		if (left() != null)
		{
			list = left().toOrderedList(list);
		}
		
		for (int ii=1;ii<=myCount;ii++)
		{
			list.add(value());
		}
		
		if (right() != null)
		{
			list = right().toOrderedList(list);
		}
		
		return list;
	}
	
	/**
	 * Adds a new node to the tree
	 * @param node
	 */
	public void insert(BinaryNode node)
	{
		if (node == null)
		{
			throw new IllegalArgumentException("'node' is a required parameter");
		}
		
		int nodeValue = node.value();
		
		if (nodeValue < value())
		{
			if (left() != null)
			{
				left().insert(node);
			}
			else
			{
				myLeft = node;
				node.myParent = this;
			}
		}
		else if (nodeValue > value())
		{
			if (right() != null)
			{
				right().insert(node);
			}
			else
			{
				myRight = node;
				node.myParent = this;
			}
		}
		else
		{
			// Values are the same
			// Count the duplicate
			myCount++;
		}
	}
}