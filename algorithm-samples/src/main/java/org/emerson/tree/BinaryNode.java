package org.emerson.tree;

import java.util.Objects;

public class BinaryNode {

	private BinaryNode parent;
	private BinaryNode left;
	private BinaryNode right;
	
	private int value;
	private int frequency = 1;
	
	/**
	 * A node is a leaf if it has no children
	 * @return
	 */
	public boolean isLeaf()
	{
		if ((Objects.isNull(left)) && (Objects.isNull(right)))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public BinaryNode(int value)
	{
		this.value = value;
	}

	public void incrementFrequency()
	{
		this.frequency++;
	}
	
	public BinaryNode getParent() {
		return parent;
	}

	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}

	public BinaryNode getLeft() {
		return left;
	}

	public void setLeft(int value)
	{
		this.setLeft(new BinaryNode(value));
	}
	
	public void setLeft(BinaryNode left)
	{
		this.left = left;
		this.left.setParent(this);
	}

	public BinaryNode getRight() {
		return right;
	}

	public void setRight(int value)
	{
		this.setRight(new BinaryNode(value));
	}
	
	public void setRight(BinaryNode right) {
		this.right = right;
		this.right.setParent(this);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}
