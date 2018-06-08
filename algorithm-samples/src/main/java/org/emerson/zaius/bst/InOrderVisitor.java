package org.emerson.zaius.bst;

public class InOrderVisitor implements Visitor
{
	private StringBuilder builder;
	
	public InOrderVisitor(StringBuilder builder)
	{
		this.builder = builder;
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
			builder.append(node.getValue()).append(",");
			this.visit(node.getLeftChild());
			this.visit(node.getRightChild());
		}
	}

}
