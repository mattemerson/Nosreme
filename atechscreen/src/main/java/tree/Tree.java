package tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree
{
	private Node root;
	private Queue<FillNode> fillQueue = new LinkedList<FillNode>();
	
	public Tree()
	{
		fillQueue.add(new FillNode(null, FillOption.ROOT));
	}
	
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		if (root != null)
		{
			toString(builder, root);
		}
		
		return builder.toString();
	}
		
	private void toString(StringBuilder builder, Node node)
	{
		if (node != null)
		{
			builder.append(node.getValue()).append("\n");
			toString(builder, node.getLeft());
			toString(builder, node.getRight());
		}
	}
	
	public void addNode(int value)
	{
		FillNode fillNode = fillQueue.poll();
		if (fillNode == null)
		{
			throw new IllegalStateException("Want to add a new node, but there is no empty space in the queue.");
		}
		
		Node parent = fillNode.getParent();
		Node node = new Node(parent, value);
		
		if (fillNode.getFillOption() == FillOption.ROOT)
		{
			this.root = node;
		}
		else if (fillNode.getFillOption() == FillOption.RIGHT)
		{
			parent.setRight(node);
		}
		else if (fillNode.getFillOption() == FillOption.LEFT)
		{
			parent.setLeft(node);
		}
		else
		{
			throw new IllegalArgumentException("FillOption='" + fillNode.getFillOption() + "' is an unknown option");
		}
		
		// Go left to right
		fillQueue.add(new FillNode(node, FillOption.LEFT));
		fillQueue.add(new FillNode(node, FillOption.RIGHT));
		
	}
	
	private enum FillOption
	{
		ROOT,
		LEFT,
		RIGHT
	}
	
	private class FillNode
	{
		private Node parent;
		private FillOption fillOption;
		
		private FillNode(Node parent, FillOption option)
		{
			this.parent = parent;
			this.fillOption = option;
		}
		
		public Node getParent()
		{
			return this.parent;
		}
		
		public FillOption getFillOption()
		{
			return this.fillOption;
		}
	}
}


