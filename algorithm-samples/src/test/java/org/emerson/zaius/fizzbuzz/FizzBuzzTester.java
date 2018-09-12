package org.emerson.zaius.fizzbuzz;

import java.util.List;

import org.emerson.file.StringFileDAO;
import org.junit.Assert;
import org.junit.Test;

public class FizzBuzzTester {

	@Test
	public void testSimpleFizzBuzzShouldPass()
	{		        
		FizzBuzzer fizzBuzzer = new SimpleFizzBuzzer();
		
		List<String> actuals = fizzBuzzer.process(1,100);
		System.out.println(actuals);
		
		String filename = "resource/test/java/org/emerson/zaius/fizzbuzz/output.txt";
		StringFileDAO dao = new StringFileDAO();
		List<String> expected = dao.readLines(filename);
		System.out.println(expected);
		
		Assert.assertArrayEquals(expected.toArray(new String[0]), actuals.toArray(new String[0]));
		System.out.println("Victory!");
	}
}
