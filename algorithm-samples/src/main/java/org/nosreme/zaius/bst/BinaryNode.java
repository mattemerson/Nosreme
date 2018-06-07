package org.nosreme.zaius.bst;

public class BinaryNode implements Visitee
{
	private int value;
	private BinaryNode leftChild;
	private BinaryNode rightChild;
	
	private boolean visited;
	
	public BinaryNode(int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return this.value;
	}
	
	public void setLeftChild(BinaryNode node)
	{
		this.leftChild = node;
	}
	
	public BinaryNode getLeftChild()
	{
		return this.leftChild;
	}
	
	public void setRightChild(BinaryNode node)
	{
		this.rightChild = node;
	}
	
	public BinaryNode getRightChild()
	{
		return this.rightChild;
	}	
	
	public void setVisited(boolean flag)
	{
		this.visited = flag;
	}
	
	public boolean getVisited()
	{
		return this.visited;
	}

	@Override
	public void accept(Visitor visitor)
	{
		visitor.visit(this);		
	}
}
