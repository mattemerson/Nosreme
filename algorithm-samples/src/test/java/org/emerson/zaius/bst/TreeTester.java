package org.emerson.zaius.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.emerson.file.StringFileDAO;
import org.emerson.zaius.bst.BinaryTree;
import org.emerson.zaius.bst.InOrderVisitor;
import org.junit.Assert;
import org.junit.Test;

public class TreeTester
{
	@Test
	public void testBreadthFirstInsertShouldPass()
	{
		String filename1 = "resources/test/org/emerson/zaius/bst/input.txt";
		StringFileDAO dao = new StringFileDAO();
		List<String> lines = dao.readLines(filename1);
		String line = lines.get(0);		
		String[] values = line.split(",");
	    System.out.println(line);
	    System.out.println(Arrays.asList(values));
	   
	    //Integer.
	    
	    
		List<Integer> inputs = Arrays.asList(values).stream().map(s->Integer.parseInt(s)).collect(Collectors.toList());
		System.out.println(inputs);		
		
		BinaryTree tree = new BinaryTree();
		for (Integer input : inputs)
		{
			tree.addNode(input);
		}
				
		List<String> actuals = new ArrayList<String>();
		InOrderVisitor visitor = new InOrderVisitor(actuals);
		tree.accept(visitor);
				
		System.out.println(actuals);
		
		String filename2 = "resources/test/org/emerson/zaius/bst/output.txt";
		List<String> expected = dao.readLines(filename2);
		System.out.println(expected);
		
		Assert.assertArrayEquals(expected.toArray(new String[0]), actuals.toArray(new String[0]));
		System.out.println("Victory!");
	}
}
