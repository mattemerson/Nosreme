package org.emerson.tree;

import java.util.Map;

import org.junit.Test;

public class BinaryTreeSumTest {

	@Test
	public void testMaxLeafSumShouldPass()
	{
		// This actually looks good!
		{
			// Answer is 27!
			MaxLeafSum leafSummer = new MaxLeafSum();		
			BinaryTree tree = newInterestingTree();		
			IntSolution solution = tree.findSum(leafSummer);
			System.out.println("solution=" + solution);
		}
		
		{
			// Answer is none!
			MaxLeafSum leafSummer = new MaxLeafSum();		
			BinaryTree tree = newOneSidedTree();		
			IntSolution solution = tree.findSum(leafSummer);
			System.out.println("solution=" + solution);
		}
		
		{
			// Answer is 14!
			MaxLeafSum leafSummer = new MaxLeafSum();		
			BinaryTree tree = newMinitree();		
			IntSolution solution = tree.findSum(leafSummer);
			System.out.println("solution=" + solution);
		}
	}
	
	@Test
	public void testMaxPossibleSumShouldPass()
	{
		{
			// Answer is 42!
			MaxPossibleSum maxSummer = new MaxPossibleSum();		
			BinaryTree tree = newMaxSumTree();		
			IntSolution solution = tree.findSum(maxSummer);
			System.out.println("solution=" + solution);
		}
	}
	
	public static BinaryTree newMaxSumTree()
	{
	    BinaryNode root = new BinaryNode(10);
	    root.setLeft(2);
	    root.setRight(10);
	    root.getLeft().setLeft(20);
	    root.getLeft().setRight(1);
	    root.getRight().setRight(-25);
	    root.getRight().getRight().setLeft(3);
	    root.getRight().getRight().setRight(4);	    
	    
	    //tree.root = new Node(10);
	    //tree.root.left = new Node(2);
	    //tree.root.right = new Node(10);
	    //tree.root.left.left = new Node(20);
	    //tree.root.left.right = new Node(1);
	    //tree.root.right.right = new Node(-25);
	    //tree.root.right.right.left = new Node(3);
	    //tree.root.right.right.right = new Node(4);
	    
	    BinaryTree tree = new BinaryTree(root);
	    
	    return tree;
	}
	
	public static BinaryTree newMinitree()
	{
		BinaryNode root = new BinaryNode(1);
		root.setLeft(5);
		root.getLeft().setLeft(3);
		root.getLeft().setRight(6);
		
	    BinaryTree tree = new BinaryTree(root);
	    
	    return tree;
	}
	
	public static BinaryTree newOneSidedTree()
	{
		BinaryNode root = new BinaryNode(1);
		root.setLeft(5);
		root.getLeft().setLeft(5);
		
	    BinaryTree tree = new BinaryTree(root);
	    
	    return tree;
	}
	
	public static BinaryTree newInterestingTree()
	{
		BinaryNode root = new BinaryNode(-15);
		root.setLeft(5);
		root.setRight(6);
		root.getLeft().setLeft(-8);
		root.getLeft().setRight(1);		
		root.getLeft().getLeft().setLeft(2);
		root.getLeft().getLeft().setRight(6);
		root.getRight().setLeft(3);
		root.getRight().setRight(9);
		root.getRight().getRight().setRight(0);
		root.getRight().getRight().getRight().setLeft(4);
		root.getRight().getRight().getRight().setRight(-1);
		root.getRight().getRight().getRight().getRight().setLeft(10);
			    
	    BinaryTree tree = new BinaryTree(root);
	    
	    return tree;
	};
}
