package org.emerson.zaius.bst;

import java.util.List;

public class InOrderVisitor implements Visitor
{
	//private StringBuilder builder;
	private List<String> collector;
	
	public InOrderVisitor(List<String> collector)
	{
		this.collector = collector;
	}
	
	@Override
	public void visit(BinaryTree tree)
	{
		visit(tree.getRoot());
	}

	@Override
	public void visit(BinaryNode node)
	{
		if (node != null)
		{
			collector.add(Integer.toString(node.getValue()));
			this.visit(node.getLeftChild());
			this.visit(node.getRightChild());
		}
	}

}
