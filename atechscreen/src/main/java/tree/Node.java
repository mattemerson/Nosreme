package tree;

public class Node
{
	private int value;
	private Node parent;
	private Node left;
	private Node right;
	
	public Node(int value)
	{
		this.value = value;
	}
	
	public Node(Node parent, int value)
	{
		this.parent = parent;
		this.value = value;
	}
	
	public int getValue()
	{
		return value;
	}
	
	public Node getLeft()
	{
		return this.left;
	}
	
	public void setLeft(Node node)
	{
		this.left = node;
	}
	
	public Node getRight()
	{
		return this.right;
	}
	
	public void setRight(Node node)
	{
		this.right = node;
	}
	
}
