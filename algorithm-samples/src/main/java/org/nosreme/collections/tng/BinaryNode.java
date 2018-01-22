package org.nosreme.collections.tng;

public class BinaryNode
{
	BinaryNode parent;
	int value;
	BinaryNode left;
	BinaryNode right;
	
	public BinaryNode(int value)
	{
		this.value = value;
	}

	public void setParent(BinaryNode node)
	{
		this.parent = node;
	}
	
	public void setLeft(BinaryNode node)
	{
		this.left = node;
	}
	
	public void setRight(BinaryNode node)
	{
		this.right = node;
	}
	
	public BinaryNode getLeft()
	{
		return this.left;
	}
	
	public BinaryNode getRight()
	{
		return this.right;
	}
	
	public int value()
	{
		return value;
	}	
	
	@Override
	public String toString()
	{
		return Integer.toString(this.value);
	}
}
