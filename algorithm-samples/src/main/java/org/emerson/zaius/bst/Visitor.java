package org.emerson.zaius.bst;

public interface Visitor
{
	void visit(BinaryTree tree);
	void visit(BinaryNode node);
}
