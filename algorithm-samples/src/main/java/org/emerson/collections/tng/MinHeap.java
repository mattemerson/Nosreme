package org.emerson.collections.tng;

public class MinHeap
{
	BinaryNode root;
	BinaryNode tip;
	
	public MinHeap()
	{
	}

	public void insert(int value)
	{
		BinaryNode node = new BinaryNode(value);
		if (root == null)
		{
			root = node;
			tip = node;
		}
		else
		{
			//root.insert;
		}
	}
	
	public int extractMin()
	{
		return root.value;
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("{");
		if (root != null)
		{
			
			builder.append(root.toString());
		}
		builder.append("}");
		
		return builder.toString();
	}
	
	
}
