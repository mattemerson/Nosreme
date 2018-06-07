package org.nosreme.zaius.bst;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class TreeTester
{
	@Test
	public void testBreadthFirstInsertShouldPass()
	{
		List<Integer> inputs = new ArrayList<Integer>();
		
		BinaryTree tree = new BinaryTree();
		for (Integer input : inputs)
		{
			tree.addNode(input);
		}
				
		StringBuilder builder = new StringBuilder();
		InOrderVisitor visitor = new InOrderVisitor(builder);
		tree.accept(visitor);
		
		String output = null; // read from output		
		Assert.assertSame(output, builder.toString());		
	}
}
